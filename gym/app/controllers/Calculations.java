package controllers;

public class Calculations {


    public static float bmi(float weight, float height)
    {
        return weight/((height/100)*(height/100));
    }

    public static String weightStatus(float weight, float height)
    {
        float bmi=bmi(weight, height);
        if (bmi<15) return "Very Severely Underweight";
        if (bmi>=15 &&bmi<16) return "Severely Underweight";
        if (bmi>=16 &&bmi<18.5) return "Underweight";
        if (bmi>=18.5 && bmi<25) return "Healthy Weight ";
        if (bmi>=25 && bmi<30) return "Overweight";
        if (bmi>=30 && bmi<35) return "Moderately Obese";
        if (bmi>=35 && bmi<40) return "Severely Obese";
        if (bmi>=40 && bmi<45) return "Very Severely Obese";
        if (bmi>=45 && bmi<50) return "Morbidly Obese";
        if (bmi>=50 && bmi<55) return "Super Obese";
        if (bmi>=55) return "Hyper Obese";

    else return "Error";
    }

    public static String idealWeight(String gender, float weight, float height)
    {
      if (gender.equals("Male") || (gender.equals("male")))
      {
        if (weight-5 > 52 +(1.9*((height-152.4)/2.54))) return "red";
        else if (weight+5 < 52 +(1.9*((height-152.4)/2.54))) return "orange";
        else return "green";
      }

        if (gender.equals("Female") || (gender.equals("female")))
        {
            if (weight-5 > 49 +(1.7*((height-152.4)/2.54))) return "red";
            else if (weight+5 < 49 +(1.7*((height-152.4)/2.54))) return "orange";
            else return "green";
        }
      return "";
    }

}
