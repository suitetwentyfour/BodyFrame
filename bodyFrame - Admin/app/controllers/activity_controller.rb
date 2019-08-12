class ActivityController < ApplicationController
  def assign_activity
    begin
      if(params[:assigner].present? and params[:assignee].present? and params[:activity_type].present? and params[:due_date].present? and params[:memo].present?)
        assigner = ActiveRecord::Base.connection.quote(params[:assigner])
        assignee = ActiveRecord::Base.connection.quote(params[:assignee])
        activity_type = ActiveRecord::Base.connection.quote(params[:activity_type])
        due_date = ActiveRecord::Base.connection.quote(params[:due_date])
        media_link = ActiveRecord::Base.connection.quote(params[:media_link])
        memo = ActiveRecord::Base.connection.quote(params[:memo])
        sqlCommand = "insert into activities(activity_type, due_date, media_link, assigner, assignee, memo) values(#{activity_type}, #{due_date}, #{media_link}, #{assigner}, #{assignee}, #{memo});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Activity successfully added."}
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

  def get_activities
    begin
      if(params[:assignee].present? and params[:activity_id].present?)
        assignee = ActiveRecord::Base.connection.quote(params[:assignee])
        activity_id = ActiveRecord::Base.connection.quote(params[:activity_id])
        # If activity_id == 0 then grab everything for that user
        sqlCommand = params[:activity_id].to_i == 0 ? "select * from activities where assignee = #{assignee}" : "select * from activities where assignee = #{assignee} and activity_id = #{activity_id};"
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

  def complete_activity
    begin
      if(params[:assignee].present? and params[:activity_id].present?)
        assignee = ActiveRecord::Base.connection.quote(params[:assignee])
        activity_id = ActiveRecord::Base.connection.quote(params[:activity_id])
        ActiveRecord::Base.connection.exec_query("update activities set completed = TRUE where assignee = #{assignee} and activity_id = #{activity_id};")
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Activity was successfully marked completed."}
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
