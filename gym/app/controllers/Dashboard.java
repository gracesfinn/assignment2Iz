package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessment;

      double bmiValue = GymUtility.calculateBMI(member, Accounts.latestAssessment(assessments));
      DecimalFormat df2 = new DecimalFormat("#.##");
      String bmiFormatted = df2.format(bmiValue);
      String weightStatus = GymUtility.determineBMICategory(bmiValue);
      Boolean idealWeight = GymUtility.isIdealBodyWeight(member, Accounts.latestAssessment(assessments));

      render("dashboard.html", member, assessments, bmiFormatted, weightStatus, idealWeight);

    }

  public static void deleteAssessment(Long id, Long aid)
  {
    Member member = Member.findById(id);
    Assessment assessments = Assessment.findById(aid);
    member.assessment.remove(assessments);
    member.save();
    assessments.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }

  public static void addAssessment(float weight, float chest, float thigh, float upperarm, float waist, float hips)
  {
    Member member = Accounts.getLoggedInMember();
    boolean trend = false;
    if (member.assessment.size()==0) {
        if (weight <= member.startWeight) trend=true;
    }
    else if (weight <= member.assessment.get(member.assessment.size()-1).weight) trend=true;
    Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips, trend);
    member.assessment.add(assessment);
    member.save();
    redirect ("/dashboard");
  }

}
