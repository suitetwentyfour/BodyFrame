class NotificationController < ApplicationController
  def send_notification
    begin
      if(params[:user_id].present? and params[:notification].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        notification = ActiveRecord::Base.connection.quote(params[:notification])
        sqlCommand = "insert into notification(user_id, notification) values(#{user_id}, #{notification});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        response = {"Reponse" => "200","Message" => "Notification successfully sent."}
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

  def get_notifications
    begin
      if(params[:user_id].present?)
        result = ActiveRecord::Base.connection.exec_query("select * from notification where user_id = #{ActiveRecord::Base.connection.quote(params[:user_id])};")
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

  def read_notification
    begin
      if(params[:user_id].present? and params[:notification_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        notification_id = ActiveRecord::Base.connection.quote(params[:notification_id])
        ActiveRecord::Base.connection.exec_query("update notification set read = TRUE where user_id = #{user_id} and notification_id = #{notification_id};")
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
