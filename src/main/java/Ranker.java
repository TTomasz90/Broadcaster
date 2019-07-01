
/** 
 *  Ranker class
 *  Author: Tomasz Turek
 *	Description: GUI companion application for visualizing Quiz results and interacting with database.
 *
 */

import java.util.ArrayList;
import java.util.List;

import contacts.Contact;
import database.DbOperations;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quiz.Quiz;
import statistics.UserStreams;

public class Ranker extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private String quizName;

	private String query;

	private Object selectedValue;

	public void start(Stage primeryStage) {

		// objects read from DAT file
		ArrayList<Contact> record = Quiz.retreveSessionRecord();

		// updated records with gradeID
		ArrayList<Contact> rankList = Quiz.assignGradeDesignators(record);

		// =================== ROOT ==================== //

		primeryStage.setTitle("Ranker");

		VBox root = new VBox();

		root.setPadding(new Insets(10));

		root.setSpacing(5);

		Label label = new Label("Quiz Results");

		Label label2 = new Label("Current Quiz Section");

		Label label3 = new Label("Quiz Database");

		// =================== TEXT AREA ==================== //

		TextArea textArea = new TextArea();

		textArea.setEditable(false);

		textArea.setStyle(" -fx-display-caret: false; -fx-focus-color: transparent;");

		textArea.setStyle("-fx-font-family: monospace");

		UserStreams.printStream(rankList, textArea);

		// =================== BUTTONS ROWS ==================== //

		HBox buttonBar = new HBox();

		buttonBar.setAlignment(Pos.CENTER);

		buttonBar.setPadding(new Insets(20, 100, 20, 80));

		buttonBar.setSpacing(10);

		HBox buttonBar2 = new HBox();

		buttonBar2.setAlignment(Pos.CENTER);

		buttonBar2.setPadding(new Insets(10, 240, 10, 240));

		buttonBar2.setSpacing(10);

		HBox buttonBar3 = new HBox();

		buttonBar3.setAlignment(Pos.BASELINE_LEFT);

		buttonBar3.setPadding(new Insets(10, 100, 10, 80));

		buttonBar3.setSpacing(10);

		HBox buttonBar4 = new HBox();

		buttonBar4.setAlignment(Pos.BASELINE_LEFT);

		buttonBar4.setPadding(new Insets(10, 100, 10, 80));

		buttonBar4.setSpacing(10);

		// =================== BUTTONS ==================== //

		// Button to Filter scores Highest -> Lowest
		Button ordByTopScoresBtn = new Button("Highest Scores");

		ordByTopScoresBtn.setStyle("-fx-color: -fx-hover-base; ");

		ordByTopScoresBtn.setOnAction(e -> {

			List<Contact> ascendingScores = UserStreams.sortStreamDescending(rankList);

			textArea.setText("");

			UserStreams.printStream(ascendingScores, textArea);

		});

		// Button to Filter scores Lowest -> Highest
		Button ordByBottomScoresBtn = new Button("Lowest Scores");

		ordByBottomScoresBtn.setStyle("-fx-color: -fx-hover-base; ");

		ordByBottomScoresBtn.setOnAction(e -> {

			List<Contact> descendingScores = UserStreams.sortStreamAscending(rankList);

			textArea.setText("");

			UserStreams.printStream(descendingScores, textArea);
		});

		// Button to view clear applied filters
		Button getCurrentQuiz = new Button("Current Quiz");

		getCurrentQuiz.setStyle("-fx-color: -fx-hover-base; ");

		getCurrentQuiz.setOnAction(e -> {

			List<Contact> scoresOnFile = Quiz.retreveSessionRecord();

			textArea.setText("");

			UserStreams.printStream(scoresOnFile, textArea);
		});

		// Button to view the top Scorer or Scorers
		Button getTopScoresBtn = new Button("Top Scores");

		getTopScoresBtn.setStyle("-fx-color: -fx-hover-base; ");

		getTopScoresBtn.setOnAction(e -> {

			List<Contact> scoresOnFile = UserStreams.topPerformers(rankList);

			textArea.setText("");

			UserStreams.printStream(scoresOnFile, textArea);

		});

		// Button to view the lowest Scorer or Scorers
		Button getLowestScoresBtn = new Button("Bottom Scores");

		getLowestScoresBtn.setStyle("-fx-color: -fx-hover-base; ");

		getLowestScoresBtn.setOnAction(e -> {

			List<Contact> scoresOnFile = UserStreams.bottomPerformers(rankList);

			textArea.setText("");

			UserStreams.printStream(scoresOnFile, textArea);

		});

		// create a textfield for naming quiz
		TextField quizName = new TextField();

		// placeholder text
		quizName.setPromptText("Enter quiz name");

		// Button for saving quiz to database
		Button saveQuizBtn = new Button("Save Quiz");

		saveQuizBtn.setStyle("-fx-color: -fx-hover-base; ");

		saveQuizBtn.setOnAction(e -> {

			this.quizName = quizName.getText();

			// if field empty
			if (this.quizName.equals("")) {

				textArea.setText("You must enter a name for your quiz before saving.");

			} else {

				rankList.forEach(student -> {

					try {
						DbOperations.postData(this.quizName, student.firstName, student.lastName,
								student.percentCorrect, student.designatedGrade);

					} catch (Exception e1) {

						e1.printStackTrace();

					} finally {
						textArea.setText("New Quiz has been added to the database");
					}
				});

			}

		});

		// Button for retrieving all DB quiz records
		Button getAllBtn = new Button("Get All Quizzes");

		getAllBtn.setPadding(new Insets(10, 100, 10, 100));

		getAllBtn.setOnAction(e -> {

			try {
				// clear textarea
				textArea.setText("");

				DbOperations.getAllData(textArea);

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		});

		// dropdown filter selector for selecting DB query constraints
		final ComboBox typeComboBox = new ComboBox();

		typeComboBox.setPromptText("Search Criteria");

		typeComboBox.getItems().addAll("First Name", "Last Name", "Quiz Name");

		TextField query = new TextField();

		query.setPromptText("Enter keyword");

		// Button for initializing DB custom query
		Button searchInputBtn = new Button("   Search  ");

		searchInputBtn.setStyle("-fx-color: -fx-hover-base; ");

		searchInputBtn.setOnAction(e -> {

			textArea.setText("");

			this.query = query.getText();

			this.selectedValue = typeComboBox.getValue();

			try {

				DbOperations.getSpecificData(textArea, this.selectedValue, this.query);

			} catch (NullPointerException e1) {

				textArea.appendText("You must select a Search criteria");

			} catch (Exception e2) {

				System.out.println(e2);
			}

		});

		// =================== SCENE ASSEMBLY ==================== //

		buttonBar.getChildren().addAll(getCurrentQuiz, ordByTopScoresBtn, ordByBottomScoresBtn, getTopScoresBtn,
				getLowestScoresBtn);

		buttonBar2.getChildren().addAll(getAllBtn);

		buttonBar3.getChildren().addAll(saveQuizBtn, quizName);

		buttonBar4.getChildren().addAll(searchInputBtn, query, typeComboBox);

		root.getChildren().addAll(label, textArea, label2, buttonBar, label3, buttonBar2, buttonBar3, buttonBar4);

		Scene scene = new Scene(root, 800, 500);

		primeryStage.setScene(scene);

		primeryStage.setResizable(false);

		primeryStage.show();

	}

}
