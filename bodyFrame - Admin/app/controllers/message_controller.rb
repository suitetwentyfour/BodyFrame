class MessageController < ApplicationController
  def send_message
    begin
      if(params[:user_id].present? and params[:send_to].present? and params[:message].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        send_to = ActiveRecord::Base.connection.quote(params[:send_to])
        message = ActiveRecord::Base.connection.quote(params[:message])
        sqlCommand = "insert into message(user_id, message_from, message) values(#{send_to}, #{user_id}, #{message});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Message successfully sent."}
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

  def get_messages
    begin
      if(params[:user_id].present?)
        result = ActiveRecord::Base.connection.exec_query("select * from message where user_id = #{ActiveRecord::Base.connection.quote(params[:user_id])};")
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

  def read_message
    begin
      if(params[:user_id].present? and params[:message_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        message_id = ActiveRecord::Base.connection.quote(params[:message_id])
        ActiveRecord::Base.connection.exec_query("update message set read = TRUE where user_id = #{user_id} and message_id = #{message_id};")
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Message was successfully marked read."}
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
