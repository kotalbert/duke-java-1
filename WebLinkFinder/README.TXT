
PROJECT TITLE: WebLinkFinder
PURPOSE OF PROJECT: Assignment 2: Finding Web Links
https://www.coursera.org/learn/java-programming/supplement/ap1pJ/programming-exercise-finding-a-gene-and-web-links
VERSION: 1.0
Write a program that reads the lines from the file at this URL location, http://www.dukelearntoprogram.com/course2/data/manylinks.html, and prints each URL on the page that is a link to youtube.com.

Assume that a link to youtube.com has no spaces in it and would be in the format (where [stuff] represents characters that are not verbatim):

“http:[stuff]youtube.com[stuff]”

Here is a suggested algorithm:

Use URLResource and read the file word by word.
For each word, check to see if “youtube.com” is in it. If it is, find the double quote to the left and right of it to identify the URL. Note, the double quotation mark is a special character in Java. To look for a double quote, look for (\”), since the backslash (\) character indicates we want the literal quotation marks (“) and not the Java character. The string you search for would be written “\”” for one double quotation mark.
In addition to the String method indexOf(x, num), you might want to consider using the String method lastIndexOf(s, num) that can be used with two parameters s and num. The parameter s is the string or character to look for, and num is the last position in the string to look for it. This method returns the last match from the start of the string up to the num position, so it is a good option for finding the opening quotation mark of a string searching backward from the youtube.com part of the URL. More information on String methods can be found in the course documentation (http://www.dukelearntoprogram.com/course2/doc) or at the Java documentation for Strings (http://docs.oracle.com/javase/7/docs/api/java/lang/String.html).

Caution: The word Youtube could appear in different cases such as YouTube or youtube or YOUTUBE. You can find the URLs easier by converting the string to lowercase. However, you will need the original string (with uppercase and lowercase letters) to view the YouTube URL to answer a quiz question because YouTube links are case sensitive. The link https://www.youtube.com/watch?v=ji5_MqicxSo is different than the link

https://www.youtube.com/watch?v=ji5_mqicxso, where all the letters are lowercase.

