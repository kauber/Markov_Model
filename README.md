# Exploring predictive text in Java

This project implements predictive text using Java, and it's part of the
assignments of the [Java programming principles of software design](https://www.coursera.org/learn/java-programming-design-principles/home/welcome) course.
The code was originally developed in BlueJ, then moved to IntelliJ and adjusted to run there.
<p>
The fundamental ideal is to generate random text by training character selection on a training text, provided in the /data folder.
</p>
The code includes a set of Markov models of n orders, where n indicates the number of characters
used to predict the following one during text generation.
<p>
The code also implements an efficient version of the model, in which predicted characters are not computed from scratch at every run, but instead stored in a hashmap to be queried multiple times.
</p>

