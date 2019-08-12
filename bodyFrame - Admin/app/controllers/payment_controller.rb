class PaymentController < ApplicationController
  def pay_app
    begin
      if(params[:email].present? and params[:stripeToken].present? and params[:amount].present? and params[:payer].present? and params[:memo].present?)
        customer = Stripe::Customer.create(
          :email => params[:email],
          :source  => params[:stripeToken]
        )

        charge = Stripe::Charge.create(
          :customer    => customer.id,
          :amount      => params[:amount].to_i,
          :description => "Payment of " + params[:amount].to_s + " from the trainer",
          :currency    => 'usd'
        )
        if(!charge.nil?)
          sqlCommand = "insert into payment_history(payment_amount, payee, payer, memo) values(#{params[:amount].to_s},'0X000000',#{params[:payer]},#{params[:memo]});"
          ActiveRecord::Base.connection.exec_query(sqlCommand)
          ActiveRecord::Base.connection.close
          response = {"Reponse" => "200","Message" => "Payment to Body Frame was successfull."}
          render json: JSON.pretty_generate(response.to_hash)
        else
          response = {"Reponse" => "500","Message" => "Payment Error in the pay_app method."}
          render json: JSON.pretty_generate(response.to_hash)
        end
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue Stripe::CardError => e
      response = {"Reponse" => "500","Message" => "The Stripe exception was '" + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end

  # Need to add taking 10% off the top and putting the rest in a trainer account
  def pay_trainer
    begin
      if(params[:email].present? and params[:stripeToken].present? and params[:amount].present? and params[:payee].present?and params[:payer].present? and params[:memo].present?)
        customer = Stripe::Customer.create(
          :email => params[:email],
          :source  => params[:stripeToken]
        )

        charge = Stripe::Charge.create(
          :customer    => customer.id,
          :amount      => params[:amount].to_i,
          :description => "Payment of " + params[:amount].to_s + " from the trainer",
          :currency    => 'usd'
        )
        if(!charge.nil?)
          sqlCommand = "insert into payment_history(payment_amount, payee, payer, memo) values(#{params[:amount].to_s},#{params[:payee]},#{params[:payer]},#{params[:memo]});"
          ActiveRecord::Base.connection.exec_query(sqlCommand)
          ActiveRecord::Base.connection.close
          response = {"Reponse" => "200","Message" => "Notification successfully sent."}
          render json: JSON.pretty_generate(response.to_hash)
        else
          response = {"Reponse" => "500","Message" => "Payment Error in the pay_trainer method."}
          render json: JSON.pretty_generate(response.to_hash)
        end
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue Stripe::CardError => e
      response = {"Reponse" => "500","Message" => "The Stripe exception was '" + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end

  def get_payment_history
    begin
      if(params[:payer].present? and params[:payment_id].present?)
        payer = ActiveRecord::Base.connection.quote(params[:payer])
        payment_id = ActiveRecord::Base.connection.quote(params[:payment_id])
        # If payment id == 0 then grab everything
        sqlCommand = params[:assignment_id] == 0 ? "select * from payment_history where payer = #{payer}" : "select * from payment_history where payer = #{payer} and payment_id = #{payment_id};"
        result = ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close
        render json: JSON.pretty_generate(result.to_hash)
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end
end
