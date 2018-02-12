# Palette Swapper

Replaces all of the colors in an image with the closet colors from another image.

## Compiling

#### Linux/Mac

`javac palette/Main.java`

#### Windows

`javac palette\Main.java`

## Usage

#### Linux/Mac

`java palette/Main \<palette source image\> \<target image\> \<output image\>`

#### Windows

`java palette\Main \<palette source image\> \<target image\> \<output image\>`

For example, if the current directory has two image files palette.png and target.png...

`java palette/Main palette.png target.png converted.png`

... would create a new file called converted.png that contains the image from target.png with the colors found
in palette.png.
