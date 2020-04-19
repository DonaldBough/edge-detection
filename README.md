# edge-detection
Basic implementation of Sobel's edge detection algorithm on pictures.

Takes an input image in the root folder and outputs the resulting image in the same folder. This was done quickly for a interview challenge, but hey it works.

## Here's What it Looks Like
![alt text](edge-detection/donald_hacking.jpg "Sample input")
![alt text](edge-detection/SobelOutput.jpg "Sample input")

Wow! The idea is that it multiples a gradient iteratively over the image, producing a kernal, and applying some other changes to make a set of resulting pixels. It was fun to implement and I'm uploading this so it doesn't get lost on my local machine. I can't say I remember everything behind Sobel's algorithm, but it was really interesting to implement at the time.
