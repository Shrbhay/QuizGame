public class QuestionBank {
    public static Question[] getQuestions() {
        return new Question[] {
            new Question("What is the capital of India?", new String[]{"Mumbai", "Delhi", "Kolkata", "Chennai"}, "Delhi"),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, "Mars"),
            new Question("Who developed Java?", new String[]{"Oracle", "James Gosling", "Microsoft", "IBM"}, "James Gosling"),
            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, "Pacific"),
            new Question("Which language runs in a web browser?", new String[]{"C", "Python", "Java", "JavaScript"}, "JavaScript")
        };
    }
}