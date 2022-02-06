# Motivation

Related discussion: [here](https://www.spigotmc.org/threads/make-a-resource-pack-corrupted-to-protect-it-like-originalrealm.487709/).

There are Minecraft resource/data pack optimizers, which can break compatibility with regular unzippers willingly or unwillingly, to reduce filesize or "protect" the files inside.

These can make unzip think that "zipfile claims to be last disk of a multi-part archive", and it terminates because "invalid zip file with overlapped components (possible zip bomb)", making unzip programs unable to unzip them.

This little program allows you to unzip such zip archives e.g. the Wynncraft resource pack, (and probably every other normal archive too). It uses the same Java utility for unzipping as Minecraft, so it won't have any problem reading the not-really-standard kindof obfuscated zip files generated specifically for Minecraft by these tools.

---
# Disclaimer

DO NOT STEAL STUFF FOR YOUR PUBLIC SERVER/RESOURSEPACK/DATAPACK, ASK THE ORIGINAL CREATORS FIRST (even if you don't make profit). However, I think it should be fine for home use ;).

---
# Usage

1. Have Java installed (Java 8 and Java 17 tested, if there is a problem, you should use the java version that Minecraft uses).
1. Download the program from [Releases](https://github.com/4321ba/PackSquash_Unzipper/releases).
1. Open a terminal.
1. Choose the directory where you downloaded the program, e.g. `cd Downloads`
1. Run the program with `java -jar Unzip.jar my_zip_file.zip` replacing `my_zip_file.zip` with your zip file

---
# Compiling

You'll need to have (Open)JDK installed.

1. Compiling to bytecode: `javac Unzip.java`
1. Creating the jar file: `jar cmvf META-INF/MANIFEST.MF Unzip.jar Unzip.class`
1. Running the program: `java -jar Unzip.jar my_zip_file.zip`
