package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TrainerCtrl extends Controller
{
    public static void index(Long id)
    {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessment;
        double bmiValue = GymUtility.calculateBMI(member, Accounts.latestAssessment(assessments));
        DecimalFormat df2 = new DecimalFormat("#.##");
        String bmiFormatted = df2.format(bmiValue);
        String weightStatus = GymUtility.determineBMICategory(bmiValue);
        Boolean idealWeight = GymUtility.isIdealBodyWeight(member, Accounts.latestAssessment(assessments));

        render("trainerdashboard.html", member, assessments, bmiFormatted, weightStatus, idealWeight);


    }

    public static void addComment(Long id, Long aid, String comment)
    {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(aid);
        assessment.comment = comment;
        assessment.save();
        Logger.info("Adding Comment");
        redirect("/trainerdashboard/"+member.id);
    }

    public static void listMembers()
    {
        List<Member> account = Member.findAll();



        render("/memberlist.html", account);
    }

}