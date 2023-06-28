package dam2.add.p21.model;

public class Pregunta {

	private String question;

	private String answer1;

	private String answer2;

	private String answer3;

	private String correct;
	
	private String correct2;

	public Pregunta(String question, String answer1, String answer2, String answer3, String correct, String correct2) {
		super();
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.correct = correct;
		this.correct2 = correct2;
	}

	public Pregunta() {
		super();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getCorrect2() {
		return correct2;
	}

	public void setCorrect2(String correct2) {
		this.correct2 = correct2;
	}
	
	
}
