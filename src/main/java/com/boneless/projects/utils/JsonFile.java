package com.boneless.projects.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.*;

public class JsonFile {

    private static final String DEFAULT_DIRECTORY = "/src/main/resources/data/";

    public static String read(String filename, String mainKey, String valueKey) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has(mainKey)) {
                Object mainValue = jsonObject.get(mainKey);

                if (mainValue instanceof JSONObject) {
                    JSONObject valuesObject = (JSONObject) mainValue;

                    if (valuesObject.has(valueKey)) {
                        Object value = valuesObject.get(valueKey);

                        // Check the type of value and handle accordingly
                        if (value instanceof String) {
                            return (String) value;
                        } else if (value instanceof Number) {
                            return String.valueOf(value);
                        } else {
                            return "invalid value type";
                        }
                    } else {
                        return "invalid key";
                    }
                } else {
                    return "invalid mainKey type";
                }
            } else {
                return "-1"; // Return a sentinel value for invalid mainKey
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "invalid key";
    }
    public static String[] readArray(String filename, String mainKey) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has(mainKey)) {
                Object mainValue = jsonObject.get(mainKey);

                if (mainValue instanceof JSONArray) {
                    JSONArray arrayValue = (JSONArray) mainValue;
                    int length = arrayValue.length();
                    String[] resultArray = new String[length];

                    for (int i = 0; i < length; i++) {
                        resultArray[i] = arrayValue.getString(i);
                    }

                    return resultArray;
                } else {
                    System.out.println("Invalid array type");
                }
            } else {
                System.out.println("Invalid mainKey");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Color[] readColorArray(String filename, String mainKey) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has(mainKey)) {
                Object mainValue = jsonObject.get(mainKey);

                if (mainValue instanceof JSONArray) {
                    JSONArray arrayValue = (JSONArray) mainValue;
                    int length = arrayValue.length();
                    Color[] resultArray = new Color[length];

                    for (int i = 0; i < length; i++) {
                        resultArray[i] = stringToColor(arrayValue.getString(i));
                    }

                    return resultArray;
                } else {
                    System.out.println("Invalid array type");
                }
            } else {
                System.out.println("Invalid mainKey");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    private static Color stringToColor(String colorString) {
        if (colorString.startsWith("Color.")) {
            try {
                // Try to get predefined color by name
                return (Color) Color.class.getField(colorString.substring(6).toLowerCase()).get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (colorString.startsWith("new Color(") && colorString.endsWith(")")) {
            try {
                // Try to parse RGB values from the string
                String[] rgbValues = colorString.substring(11, colorString.length() - 1).split(",");
                int r = Integer.parseInt(rgbValues[0].trim());
                int g = Integer.parseInt(rgbValues[1].trim());
                int b = Integer.parseInt(rgbValues[2].trim());

                return new Color(r, g, b);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            // Try to get a color by the name directly (case-insensitive)
            try {
                java.lang.reflect.Field field = Color.class.getDeclaredField(colorString.toLowerCase());
                return (Color) field.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    private static int getColorByName(String colorName) {
        try {
            // Use reflection to get the color constant from the Color class
            java.lang.reflect.Field field = Color.class.getField(colorName.toLowerCase());
            return (int) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0; // Default color if not found
    }

    public static void writeToArray(String filename, String mainKey, String data) {
        try {
            JSONObject jsonObject = readJsonObject(filename);

            if (jsonObject == null) {
                jsonObject = new JSONObject();
            }

            JSONArray arrayValue = jsonObject.optJSONArray(mainKey);
            if (arrayValue == null) {
                arrayValue = new JSONArray();
                jsonObject.put(mainKey, arrayValue);
            }

            arrayValue.put(data);

            try (Writer writer = new FileWriter(getFilePath(filename))) {
                writer.write(jsonObject.toString(2)); // Indentation for better readability
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readWithThreeKeys(String filename, String mainKey, String subKey, String valueKey) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has(mainKey)) {
                Object mainValue = jsonObject.get(mainKey);

                if (mainValue instanceof JSONObject) {
                    JSONObject subObject = (JSONObject) mainValue;

                    if (subObject.has(subKey)) {
                        Object valuesObject = subObject.get(subKey);

                        if (valuesObject instanceof JSONObject) {
                            JSONObject valueObject = (JSONObject) valuesObject;

                            if (valueObject.has(valueKey)) {
                                Object value = valueObject.get(valueKey);

                                // Check the type of value and handle accordingly
                                if (value instanceof String) {
                                    return (String) value;
                                } else if (value instanceof Number) {
                                    return String.valueOf(value);
                                } else {
                                    return "invalid value type";
                                }
                            } else {
                                return "invalid key";
                            }
                        } else {
                            return "invalid subKey type";
                        }
                    } else {
                        return "invalid subKey";
                    }
                } else {
                    return "invalid mainKey type";
                }
            } else {
                return "-1"; // Return a sentinel value for invalid mainKey
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "invalid key";
    }

    public static String[][] readTiles(String filename, String mainKey, String subKey) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has(mainKey)) {
                Object mainValue = jsonObject.get(mainKey);

                if (mainValue instanceof JSONObject) {
                    JSONObject subObject = (JSONObject) mainValue;

                    if (subObject.has(subKey)) {
                        Object tilesValue = subObject.get(subKey);

                        if (tilesValue instanceof JSONArray) {
                            JSONArray tilesArray = (JSONArray) tilesValue;
                            int rows = tilesArray.length();
                            int columns = ((JSONArray) tilesArray.get(0)).length();
                            String[][] tiles = new String[rows][columns];

                            for (int i = 0; i < rows; i++) {
                                JSONArray rowArray = (JSONArray) tilesArray.get(i);

                                for (int j = 0; j < columns; j++) {
                                    tiles[i][j] = rowArray.getString(j);
                                }
                            }
                            return tiles;
                        } else {
                            System.out.println("Invalid tiles array type");
                        }
                    } else {
                        System.out.println("Invalid subKey for tiles");
                    }
                } else {
                    System.out.println("Invalid mainKey type");
                }
            } else {
                System.out.println("Invalid mainKey");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static void writeln(String filename, String mainKey, String valueKey, String value) {
        try {
            JSONObject jsonObject = readJsonObject(filename);

            if (jsonObject == null) {
                jsonObject = new JSONObject();
            }

            JSONObject valuesObject = jsonObject.optJSONObject(mainKey);
            if (valuesObject == null) {
                valuesObject = new JSONObject();
                jsonObject.put(mainKey, valuesObject);
            }

            valuesObject.put(valueKey, value);

            try (Writer writer = new FileWriter(getFilePath(filename))) {
                writer.write(jsonObject.toString(2)); // Indentation for better readability
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create(String filename, String mainKey, String valueKey, String value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(mainKey, new JSONObject());
            jsonObject.getJSONObject(mainKey).put(valueKey, value);

            try (Writer writer = new FileWriter(getFilePath(filename))) {
                writer.write(jsonObject.toString(2)); // Indentation for better readability
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFilePath(String filename) {
        String directory = System.getProperty("user.dir") + DEFAULT_DIRECTORY;
        return directory + filename;
    }

    private static JSONObject readJsonObject(String filename) {
        try (Reader reader = new FileReader(getFilePath(filename))) {
            JSONTokener tokener = new JSONTokener(reader);
            return new JSONObject(tokener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
