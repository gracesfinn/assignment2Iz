package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login(boolean failedAttempt)
    {

        render("login.html", failedAttempt);
    }

    public static void index()
    {
        Member member = getLoggedInMember();
        render("/settings.html", member);
    }

    public static void register(String firstname, String lastname, String gender, String email, String password, String address, float height, float startWeight)
    {
        gender = gender.substring(0, 1).toUpperCase();
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, gender, email, password, address, height, startWeight);
        member.save();
        authenticate(email, password);
    }

    public static void updateMember(String firstname, String lastname, String gender, String email, String password, String address, float height, float startWeight)
    {
        Logger.info("Updating Member " + email);
        Member member = getLoggedInMember();
        member.firstname = firstname;
        member.lastname = lastname;
        gender = gender.substring(0, 1).toUpperCase();
        member.gender = gender;
        member.email= email;
        member.password = password;
        member.address= address;
        member.height = height;
        member.startWeight = startWeight;
        member.save();
        redirect("/dashboard");
    }

    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        member.delete();
        redirect("/memberlist");
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            if (member.email.equals("jim@trainer.com") || member.email.equals("jimswife@trainer.com")) redirect("/memberlist");
            else redirect ("/dashboard");
        } else {
            Logger.info("Authentication failed");
            boolean failedAttempt = true;
            login(failedAttempt);
        }
    }

    public static Assessment latestAssessment(List<Assessment> assessments) {
        if (!assessments.isEmpty())  return assessments.get((assessments.size()-1));
        else return null;
    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            boolean failedAttempt=false;
            login(failedAttempt);
        }
        return member;
    }
}
