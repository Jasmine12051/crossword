package edu.jsu.mcis.cs408.crosswordmagic.controller;

public class CrosswordMagicController extends AbstractController {

    public static final String TEST_PROPERTY = "TestProperty";
    public static final String GRID_DIMENSION_PROPERTY = "GridDimension";

    public void getTestProperty(String value) {
        getModelProperty(TEST_PROPERTY);
    }

    public void getGridDimensionProperty(Integer[] value) {
        getModelProperty(GRID_DIMENSION_PROPERTY);
    }


}