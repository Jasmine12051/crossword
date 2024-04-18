package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;

import java.util.HashMap;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {

    private final int DEFAULT_PUZZLE_ID = 1;

    private Puzzle puzzle;
    private String userInput;
    private int selectedBox;

    public CrosswordMagicModel(Context context) {

        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);

    }

    public void getTestProperty() {

        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);

    }

    public void getGridDimensions(){
        int height = puzzle.getHeight();
        int width = puzzle.getWidth();
        Integer[] dimensions = new Integer[2];
        dimensions[0] = height;
        dimensions[1] = width;

        firePropertyChange(CrosswordMagicController.GRID_DIMENSIONS_PROPERTY, null, dimensions);

    }

    public void getGridNumbers(){
        Integer [][] numbers = puzzle.getNumbers();
        firePropertyChange(CrosswordMagicController.GRID_NUMBERS_PROPERTY, null, numbers);

    }

    public void getGridLetters(){
        Character [][] letters = puzzle.getLetters();
        firePropertyChange(CrosswordMagicController.GRID_LETTERS_PROPERTY, null, letters);

    }

    public void getCluesAcross() {
        firePropertyChange(CrosswordMagicController.CLUES_ACROSS_PROPERTY, null, puzzle.getCluesAcross());
    }

    public void getCluesDown() {
        firePropertyChange(CrosswordMagicController.CLUES_DOWN_PROPERTY, null, puzzle.getCluesDown());
    }

    public void setGuess(HashMap<String, String> params) {

        if (params != null) {

            Integer num = Integer.parseInt(params.get("num"));
            String guess = params.get("guess").toUpperCase().trim();

            WordDirection result = puzzle.checkGuess(num, guess);

        }

    }

    public void setUserInput(String userInput) {
        String oldValue = this.userInput;
        this.userInput = userInput;
        firePropertyChange(CrosswordMagicController.USER_INPUT_PROPERTY, oldValue, userInput);
    }

    public void setSelectedBox(int selectedBox) {
        int oldValue = this.selectedBox;
        this.selectedBox = selectedBox;
        firePropertyChange(CrosswordMagicController.SELECTED_BOX_PROPERTY, oldValue, selectedBox);
    }


}