class SupportController < ApplicationController
  def send_support_ticket
    begin
      if(params[:user_id].present? and params[:ticket_information].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        ticket_information = ActiveRecord::Base.connection.quote(params[:ticket_information])
        sqlCommand = "insert into support_ticket(user_id, ticket_information) values(#{user_id}, #{ticket_information});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        response = {"Reponse" => "200","Message" => "Ticket successfully created."}
        render json: JSON.pretty_generate(response.to_hash)
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end

  def get_support_ticket
    begin
      if(params[:user_id].present? and params[:ticket_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        ticket_id = ActiveRecord::Base.connection.quote(params[:ticket_id])
        # If message id == 0 then grab everything
        sqlCommand = params[:ticket_id].to_i == 0 ? "select * from support_ticket where user_id = #{user_id}" : "select * from support_ticket where user_id = #{user_id} and ticket_id = #{ticket_id};"
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

  def update_support_ticket
    begin
      if(params[:user_id].present? and params[:ticket_id].present? and params[:status].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        ticket_id = ActiveRecord::Base.connection.quote(params[:ticket_id])
        status = ActiveRecord::Base.connection.quote(params[:status])
        ActiveRecord::Base.connection.exec_query("update support_ticket set status = #{status} where user_id = #{user_id} and ticket_id = #{ticket_id};")
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Ticket was successfully updated."}
        render json: JSON.pretty_generate(response.to_hash)
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
