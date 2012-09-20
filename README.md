ebiggers_COMP225_ConwaysGameOfLife
===================

Comp 225: Software Design and Development
Eric Biggers

For my test project, I starting implementing an Android app for Conway's Game of
Life, which is a cellular automaton.  The app shows the evolution of the
cellular automaton over time.

The app is partially based on the Sudoku code, but I implemented an activity for
showing a view of the automaton and a view that draws the window for the
automaton.   GameOfLife.java contains the code for updating the grid of the
automaton (which is a byte[][].)  GameOfLifeView.java is a View that draws the
grid to an android Canvas whenever the view is invalidated.

One problem in designing the app is that the grid needs to be updated at regular
intervals.  To do this, a thread was created in GameOfLife.java that
asynchronously updates the grid every 300 milliseconds.  It then signals the
main thread using the android message queue interface (android.os.Handler,
android.os.Message), and the main thread invalidates the view when it receives
the message.  Note that it is not possible for the view to be invalidated by any
thread other than the main thread.

The app only shows a hard-coded pattern and grid size and allows no user
interaction.  This is obviously something that should be worked on to improve
the app.


