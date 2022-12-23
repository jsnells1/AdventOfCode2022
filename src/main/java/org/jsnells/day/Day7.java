package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Day7 extends Day {
    @Override
    public Object partOne() {
        var root = buildFileStructure();

        root.computeSize();

        return root.filesUnder100000().stream().mapToLong(f -> f.size).sum();
    }

    @Override
    public Object partTwo() {
        var root = buildFileStructure();
        root.computeSize();


        var targetSpace = 70000000;
        var unusedSpace = targetSpace - root.size;
        var neededSpace = 30000000 - unusedSpace;

        return root.filesOverValue(neededSpace).stream().mapToLong(f -> f.size).min().getAsLong();
    }

    private ElfFile buildFileStructure() {
        var lines = this.getInputText().split(NEW_LINE_SEPARATOR);

        var root = new ElfFile(0, "/", true);
        var currentFile = root;
        for (var line : lines) {
            if (line.equals("$ cd /")) {
                continue;
            }
            var parts = line.split(" ");
            if (parts[0].equals("$")) {
                if (parts[1].equals("cd")) {
                    if (parts[2].equals("..")) {
                        currentFile = currentFile.parent;
                    } else {
                        currentFile = currentFile.putChildIfAbsent(0, parts[2], true);
                    }
                }
            } else {
                if (parts[0].equals("dir")) {
                    currentFile.putChildIfAbsent(0, parts[1], true);
                } else {
                    currentFile.putChildIfAbsent(Integer.parseInt(parts[0]), parts[1], false);
                }
            }
        }

        return root;
    }


    private static class ElfFile {
        private final List<ElfFile> children = new ArrayList<>();
        private final ElfFile parent;
        private final String name;
        private final boolean isDir;
        private long size;

        public ElfFile(long size, String name, boolean isDir) {
            this.parent = this;
            this.size = size;
            this.name = name;
            this.isDir = isDir;
        }

        private ElfFile(ElfFile parent, long size, String name, boolean isDir) {
            this.parent = parent;
            this.size = size;
            this.name = name;
            this.isDir = isDir;
        }

        public ElfFile putChildIfAbsent(long childSize, String childName, boolean childIsDir) {
            var optionalChild = children.stream().filter(c -> c.name.equals(childName)).findFirst();

            if (optionalChild.isPresent()) {
                return optionalChild.get();
            }

            var child = new ElfFile(this, childSize, childName, childIsDir);
            children.add(child);
            return child;
        }

        public long computeSize() {
            if (!isDir) {
                return this.size;
            }

            this.size = this.children.stream().mapToLong(ElfFile::computeSize).sum();
            return this.size;
        }

        public List<ElfFile> filesUnder100000() {
            List<ElfFile> files = new ArrayList<>();

            if (this.size <= 100000 && isDir) {
                files.add(this);
            }

            for (var child : children) {
                files.addAll(child.filesUnder100000());
            }

            return files;
        }

        public List<ElfFile> filesOverValue(long value) {
            List<ElfFile> files = new ArrayList<>();

            if (this.size >= value && isDir) {
                files.add(this);
            }

            for (var child : children) {
                files.addAll(child.filesOverValue(value));
            }

            return files;
        }
    }
}
