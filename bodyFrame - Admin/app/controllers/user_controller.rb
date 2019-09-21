class UserController < ApplicationController
  def login
    begin
      if(params[:email].present? and params[:password].present?)
        email = ActiveRecord::Base.connection.quote(params[:email].strip.downcase)
        password = ActiveRecord::Base.connection.quote(params[:password])
        result = ActiveRecord::Base.connection.exec_query("select user_id, user_type, email, assigned_to, member_since, paid_until from users where email = #{email} and password = #{password};")
        ActiveRecord::Base.connection.close
        if(!result.blank?)
          ActiveRecord::Base.connection.exec_query("update users set last_sign_in = now();")
          ActiveRecord::Base.connection.close
        end
        render json: JSON.pretty_generate(result.blank? ? {"Reponse" => "400","Message" => "Incorrect username/password combo"}.to_hash : result.to_hash)
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end

  def create_user
    begin
      if(params[:email].present? and params[:password].present? and params[:user_type].present? and params[:first_name].present? and params[:last_name].present? and params[:birth_day].present? and
        params[:address].present? and params[:phone_number].present? and params[:sex].present? and params[:goal].present? and params[:weight].present? and params[:height].present?)
        user_id = ActiveRecord::Base.connection.quote("0X" + ActiveRecord::Base.connection.exec_query("select count(*) from users;").first['count'].to_s.rjust(6, "0"))
        ActiveRecord::Base.connection.close

        email = ActiveRecord::Base.connection.quote(params[:email].strip.downcase)
        password = ActiveRecord::Base.connection.quote(params[:password])
        user_type = ActiveRecord::Base.connection.quote(params[:user_type])
        sqlCommand = "insert into users(user_id, password, user_type, email) values(#{user_id}, #{password}, #{user_type}, #{email});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close

        if(params[:assigned_to].present?)
          assigned_to = ActiveRecord::Base.connection.quote(params[:assigned_to])
          sqlCommand = "update users set assigned_to = #{assigned_to} where user_id = #{user_id};"
          ActiveRecord::Base.connection.exec_query(sqlCommand)
          ActiveRecord::Base.connection.close
        end

        first_name = ActiveRecord::Base.connection.quote(params[:first_name].titleize)
        last_name = ActiveRecord::Base.connection.quote(params[:last_name].titleize)
        birth_day = ActiveRecord::Base.connection.quote(params[:birth_day])
        address = ActiveRecord::Base.connection.quote(params[:address])
        phone_number = ActiveRecord::Base.connection.quote(params[:phone_number])
        sex = ActiveRecord::Base.connection.quote(params[:sex])
        goal = ActiveRecord::Base.connection.quote(params[:goal])
        weight = ActiveRecord::Base.connection.quote(params[:weight])
        height = ActiveRecord::Base.connection.quote(params[:height])
        sqlCommand = "insert into user_profile(user_id, first_name, last_name, birth_day, address, phone_number, sex, goal, weight, height) " +
                     "values(#{user_id}, #{first_name}, #{last_name}, #{birth_day}, #{address}, #{phone_number}, #{sex}, #{goal}, #{weight}, #{height});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close

        response = {"Reponse" => "200","Message" => "User successfully created."}
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

  def update_user
    begin
      if(params[:user_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        if(params[:email].present?)
          email = ActiveRecord::Base.connection.quote(params[:email].strip.downcase)
          ActiveRecord::Base.connection.exec_query("update users set email = #{email} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:password].present?)
          password = ActiveRecord::Base.connection.quote(params[:password])
          ActiveRecord::Base.connection.exec_query("update users set password = #{password} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        response = {"Reponse" => "200","Message" => "User was successfully updated."}
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

  def get_profile
    begin
      if(params[:user_id].present?)
        result = ActiveRecord::Base.connection.exec_query("select * from user_profile where user_id = #{ActiveRecord::Base.connection.quote(params[:user_id])};")
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

  def update_profile
    begin
      if(params[:user_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        if(params[:first_name].present?)
          first_name = ActiveRecord::Base.connection.quote(params[:first_name].titleize)
          ActiveRecord::Base.connection.exec_query("update user_profile set first_name = #{first_name} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:last_name].present?)
          last_name = ActiveRecord::Base.connection.quote(params[:last_name].titleize)
          ActiveRecord::Base.connection.exec_query("update user_profile set last_name = #{last_name} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:birth_day].present?)
          birth_day = ActiveRecord::Base.connection.quote(params[:birth_day])
          ActiveRecord::Base.connection.exec_query("update user_profile set birth_day = #{birth_day} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:address].present?)
          address = ActiveRecord::Base.connection.quote(params[:address])
          ActiveRecord::Base.connection.exec_query("update user_profile set address = #{address} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:phone_number].present?)
          phone_number = ActiveRecord::Base.connection.quote(params[:phone_number])
          ActiveRecord::Base.connection.exec_query("update user_profile set phone_number = #{phone_number} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:sex].present?)
          sex = ActiveRecord::Base.connection.quote(params[:sex])
          ActiveRecord::Base.connection.exec_query("update user_profile set sex = #{sex} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:goal].present?)
          goal = ActiveRecord::Base.connection.quote(params[:goal])
          ActiveRecord::Base.connection.exec_query("update user_profile set goal = #{goal} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:weight].present?)
          weight = ActiveRecord::Base.connection.quote(params[:weight])
          ActiveRecord::Base.connection.exec_query("update user_profile set weight = #{weight} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        if(params[:height].present?)
          height = ActiveRecord::Base.connection.quote(params[:height])
          ActiveRecord::Base.connection.exec_query("update user_profile set height = #{height} where user_id = #{user_id};")
          ActiveRecord::Base.connection.close
        end
        response = {"Reponse" => "200","Message" => "Profile was successfully updated."}
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

  def set_card_information
    begin
      if(params[:user_id].present? and params[:first_name].present? and params[:last_name].present? and params[:card_number].present? and params[:expiration].present? and params[:CVV].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        first_name = ActiveRecord::Base.connection.quote(params[:first_name])
        last_name = ActiveRecord::Base.connection.quote(params[:last_name])
        card_number = ActiveRecord::Base.connection.quote(params[:card_number])
        expiration = ActiveRecord::Base.connection.quote(params[:expiration])
        cvv = ActiveRecord::Base.connection.quote(params[:CVV])
        sqlCommand = "insert into user_card_information(user_id, first_name, last_name, card_number, expiration, CVV) values(#{user_id}, #{first_name}, #{last_name}, #{card_number}, #{expiration}, #{cvv});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Card information successfully added."}
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

  def get_card_information
    begin
      if(params[:user_id].present?)
        result = ActiveRecord::Base.connection.exec_query("select * from user_card_information where user_id = #{ActiveRecord::Base.connection.quote(params[:user_id])};")
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

  def update_card_information
    begin
      if(params[:user_id].present? and params[:first_name].present? and params[:last_name].present? and params[:card_number].present? and params[:expiration].present? and params[:CVV].present?)
        sqlCommand = "delete from user_card_information where user_id = #{ActiveRecord::Base.connection.quote(params[:user_id])};"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close

        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        first_name = ActiveRecord::Base.connection.quote(params[:first_name])
        last_name = ActiveRecord::Base.connection.quote(params[:last_name])
        card_number = ActiveRecord::Base.connection.quote(params[:card_number])
        expiration = ActiveRecord::Base.connection.quote(params[:expiration])
        cvv = ActiveRecord::Base.connection.quote(params[:CVV])
        sqlCommand = "insert into user_card_information(user_id, first_name, last_name, card_number, expiration, CVV) values(#{user_id}, #{first_name}, #{last_name}, #{card_number}, #{expiration}, #{cvv});"
        ActiveRecord::Base.connection.exec_query(sqlCommand)
        ActiveRecord::Base.connection.close
        response = {"Reponse" => "200","Message" => "Card information was successfully updated."}
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

  def forgot_password
    begin
      if(params[:email].present?)
        email_exists = ActiveRecord::Base.connection.exec_query("select count(*) from users where email = #{ActiveRecord::Base.connection.quote(params[:email].strip.downcase)};").first['count'].to_i == 1 ? true : false
        ActiveRecord::Base.connection.close

        if(email_exists)
          result = ActiveRecord::Base.connection.exec_query("select first_name, last_name from users inner join user_profile on users.user_id = user_profile.user_id where email = #{ActiveRecord::Base.connection.quote(params[:email].downcase)};")
          first_name = result.first['first_name']
          last_name = result.first['last_name']
          email = params[:email].strip.downcase
          password  = SecureRandom.hex(5).upcase
          Mailer.reset_password_email(first_name, last_name, email, password).deliver
          ActiveRecord::Base.connection.close
          sqlCommand = "update users set password = '" + password + "' where email = '" + params[:email].downcase + "';"
          ActiveRecord::Base.connection.exec_query(sqlCommand)
          ActiveRecord::Base.connection.close

          response = {"Reponse" => "200","Message" => "Password was reset and an email sent."}
          render json: JSON.pretty_generate(response.to_hash)
        else
          response = {"Reponse" => "200","Message" => "Email not valid."}
          render json: JSON.pretty_generate(response.to_hash)
        end
      else
        response = {"Reponse" => "412","Message" => "Incorrect number of parameters"}
        render json: JSON.pretty_generate(response.to_hash)
      end
    rescue StandardError => e
      response = {"Reponse" => "500","Message" => "Page error, " + e.message}
      render json: JSON.pretty_generate(response.to_hash)
    end
  end

  def get_contact_list
    begin
      if(params[:user_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        sql_command = "select users.user_id, first_name, last_name from users inner join user_profile on users.user_id = user_profile.user_id where assigned_to = #{user_id};"
        result = ActiveRecord::Base.connection.exec_query(sql_command)
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

  def comp_user
    begin
      if(params[:user_id].present? and params[:comp_time].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        comp_time = ActiveRecord::Base.connection.quote(params[:comp_time])
        sql_command = "update users set paid_until = #{comp_time} where user_id = #{user_id};"
        result = ActiveRecord::Base.connection.exec_query(sql_command)
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

  def delete_user_account
    begin
      if(params[:user_id].present?)
        user_id = ActiveRecord::Base.connection.quote(params[:user_id])
        sql_command = "update users set email = (email || '-deleted ' || CURRENT_DATE) where user_id = #{user_id};"
        ActiveRecord::Base.connection.exec_query(sql_command)
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
