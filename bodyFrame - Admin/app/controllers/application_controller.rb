class ApplicationController < ActionController::Base
  protect_from_forgery with: :null_session, only: [:create]

  def error_500
    sqlCommand = "insert into support_ticket(user_id, ticket_information, status) values('0X000000', 'Error 500 occured in the admin panel', 'CLOSED');"
    ActiveRecord::Base.connection.exec_query(sqlCommand)
    ActiveRecord::Base.connection.close
    response = {"Reponse" => "500","Message" => "There was a problem with the application, it has been logged in the ticketing system."}
    render json: JSON.pretty_generate(response.to_hash)
  end

  def error_404
    response = {"Reponse" => "404","Message" => "You attempted to reach a page that does not exist please use the documentation for help."}
    render json: JSON.pretty_generate(response.to_hash)
  end
end
