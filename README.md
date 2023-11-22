# Projects (mostly documentation for Neighborhood)
## Majority of the files here (in com.boneless):

# Utils
## AudioPlayer
AudioPlayer takes 1 parameter, that being the file name and plays the provided Audio File.
### Usage
AudioPlayer will auto fill the directory to the path of 
*src/main/resources/assets/* all thats needed is the filename.

#### AudioPlayer.play(filename);
AudioPlayer does not need to its own instance, it can be called on via
AudioPlayer.play("filename.wav"), without having to create a new instance.
Only .wav is supported. If the file is in a different directory that 
is not in the provided directory, you can directly provide a new one
and the class will adjust accordingly.

# --------------------------------------------------------

## FileReaderSaver --Depreciated--
used to read and save file, replaced in favor of Json Files

# --------------------------------------------------------

## IconResize
Takes an image from the assets directory and uses them as a imageIcon.

### Usage
#### *IconResize icon = new IconResize("filename", sizeX, sizeY);*
Uses the provided png to be used a ImageIcon, and will resize it
to the desired size
#### *IconResize icon = new IconResize("filename");*
Uses the provided png to be used a ImageIcon, uses default image size

# --------------------------------------------------------

## JsonFile
Reads or Write data from the default directory and provided file in the
JSON file format. Can be used without new instance
### Usage
#### *JsonFile.read("filename", "mainKey", "valueKey");*
Reads data from the provided data.<br>
````
{
    "mainKey": {
        "valueKey": "data"
    }
}
````
will return "data" as a String value
#### *JsonFile.readArray("filename", "mainKey");*
Reads data form the provided file, and will output an array compatible
String
````
{
  "mainKey": [
    "data1",
    "data2",
    "data3"
  ]
}
````
Output needs to be saved to a String[] array, or converted to another
value such as int if numbers are provided
String[] string = JsonFile.readArray("filename", "mainKey");
for int conversion:<br>
int[] ints = Arrays.stream(string).mapToInt(Integer::parseInt).toArray();
#### *JsonFile.readColorArray("filename", "mainKey");*
Reads data form the provided file, and will output an array compatible
String
````
{
  "mainKey": [
    Color.red,
    Color.orange,
    Color.yellow,
    Color.green,
    Color.cyan,
    Color.blue,
    new Color(255, 105, 180),
    new Color(150,0,255),
    new Color(139, 69, 19),
    Color.white,
    new Color(200, 200, 200),
    Color.gray,
    new Color(69, 69, 69),
    Color.black
  ]
}
````
Output needs to be saved to a Color[] array
#### *JsonFile.writeToArray("filename", "mainKey", "data");*
Saves data with the provided parameters
<br>
Example:<br>
JsonFile.writeToArray("filename", "mainKey", "this is data");
<br>will save:<br>
````
{
  "mainKey": [
    "this is data"
  ]
}
````
#### *JsonFile.writeln("filename", "mainKey", "valueKey", "data");*
Saves data with the provided parameters
<br>
Example:<br>
JsonFile.writeToArray("filename", "mainKey", "this is data");
<br>will save:<br>
````
{
  "mainKey": {
    "this is data"
  }
}
````
This can be stacked, so it will add data on top of other data without
overwriting
# --------------------------------------------------------
## NormalButtons
Sets any unset JButtons to the OS default
### Usage
#### NormalButtons.set();
# --------------------------------------------------------
## VideoPlayer
!! DOES NOT WORK (yet) !! <br>
will play audio, but will not play video
# --------------------------------------------------------
# Neighborhood
Copys almost all features from
https://studio.code.org/docs/ide/javalab/classes/Painter <br>
There are some changes that need to be made, such as using String instead
of Color for painting.
## Painter
Nothing new here
### PainterListener (private)
PainterListener is my "spin" on the painter. adds KeyListener, a color
pallet, and the ability to save custom colors via RGB values. <br>
to use this new addon, all that is need is to use <br>
````Painter name = new Painter(true)```` <br>
from there, 2 new windows will show, the main Painter window, and
the color pallet window with 14 preset colors.