cd C:\Users\WillN\Documents\College\CompetitiveProgramming\Halite3\Halite3Round2
call run_clean.bat
javac DumbBot.java
javac MyBot.java
halite.exe --replay-directory replays/ -vvv --width 32 --height 32  "java MyBot" "java DumbBot"
rm *.class
cp replays/*.hlt ./
ren *.hlt replay.hlt
C:\Users\WillN\Documents\College\CompetitiveProgramming\Halite3\fluorine\Fluorine.exe replay.hlt