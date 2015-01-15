package sph.durga.sexercisetracker;

import org.achartengine.chart.PointStyle;

import android.graphics.Color;

public class Constants
{
public final static String[] mon = { "jan", "feb", "mar",
			"apr", "may", "jun", "jul", "aug", "sep",
			"oct", "nov", "dec" };
public static int currentYear = 2015;
//static String currentYearStr = "2015";
static String[] workoutArray = {"cardio", "swimming", "bike", "Abs", "walking"};
static String[] tewntyfivePointRecipes = {"ginger chutney", "eggplant curd chutney", "donut", "parfait"};
static String[] fortyPointRecipes = {"kandi podi", "sambhar", "coconut rice", "tomato rice", "bhel puri", "papdi chat", "muffin", "brownie", "choclate", "chole palak", "Mango rice" };
static String[] fiftyfivePointRecipes = {"kheer", "icecream", "parvannam", "pie", "pan cakes",  "dum aloo", "jhal muri", "Bread bonda", "paneer bonda", "dahi vada", "baked sweet potato", "Cajun fries", "burger" };
static String[] totalhundredPointRecipes = { "waffle", "banana pudding", "gulab jamun", "rava ladoo", "fruit custard", "coconut ladoo", "vada pav", "poori"};
static String[] totaltwohundredPointRecipes = {"bobattu", "kaju burfi", "badam burfi", "mysore pak", "cup cakes", "malai kofta", "chole batura", "poori"};
public static final int[] COLORSARR = {Color.parseColor("#F2846B"),
    Color.parseColor("#A01115"), Color.parseColor("#741E1E"), Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.YELLOW, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.MAGENTA, Color.TRANSPARENT};
public static final PointStyle[] pointStylesArray = {PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.POINT, PointStyle.SQUARE, PointStyle.TRIANGLE};

public static final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
		31, 30, 31 };
}
