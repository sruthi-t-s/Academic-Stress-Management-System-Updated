public abstract class StressTips implements StressAdvice {

    public abstract String getTips(); 
}

class LowStressTips extends StressTips {

    @Override
    public String getTips() {
        return "Keep following your study schedule.\n"
             + "Take regular breaks.\n"
             + "Maintain healthy sleep habits.";
    }
}

class ModerateStressTips extends StressTips {

    @Override
    public String getTips() {
        return "Divide your work into smaller tasks.\n"
             + "Take breaks between study sessions.\n"
             + "Practice relaxation exercises.";
    }
}

class HighStressTips extends StressTips {

    @Override
    public String getTips() {
        return "Reduce unnecessary workload.\n"
             + "Take sufficient rest.\n"
             + "Talk to someone you trust.";
    }
}