
PROJECT TITLE: GeneFinder
PURPOSE OF PROJECT: Assignment 1: Finding a Gene
https://www.coursera.org/learn/java-programming/supplement/ap1pJ/programming-exercise-finding-a-gene-and-web-links
VERSION: 1.0

Algorithm to identify a gene in a strand of DNA:

Identify the first start codon ATG in the string.
Identify the first occurrence of the stop codon TAG after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the beginning of the start codon to the end of the TAG stop codon.
If no gene is found yet, then identify the first occurrence of the stop codon TGA after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the start codon to the TGA stop codon.
If no gene is found yet, then identify the first occurrence of the stop codon TAA after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the start codon to the TAA stop codon.
If no gene is found yet, then the gene returned is the empty string.
If a gene was found, then print the gene, and also print the stop codon that was used to find the gene.
Your program should work regardless of whether the DNA strand uses uppercase or lowercase letters.