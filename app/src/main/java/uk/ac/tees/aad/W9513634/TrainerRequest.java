package uk.ac.tees.aad.W9513634;

public class TrainerRequest {
    String date;
    String option1;
    String option2;
    String option3;

    public TrainerRequest(String date, String option1, String option2, String option3) {
        this.date = date;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    public TrainerRequest() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
}
