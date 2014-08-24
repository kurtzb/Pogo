package me.nrubin29.pogo.lang2;

import me.nrubin29.pogo.Pogo;
import me.nrubin29.pogo.ide.Console;

import java.io.IOException;
import java.util.ArrayList;

public class If extends ConditionalBlock {

    private ArrayList<ElseIf> elseIfs;
    private Else elze;

    public If(Block superBlock, Value a, Value b, Comparison comparison) {
        super(superBlock, a, b, comparison);

        this.elseIfs = new ArrayList<>();
    }

    public ElseIf addElseIf(ElseIf elseIf) {
        elseIfs.add(elseIf);
        return elseIf;
    }

    public void setElse(Else elze) {
        this.elze = elze;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Pogo.getIDE().getConsole().write("run() called on " + toString(), Console.MessageType.OUTPUT);
        Pogo.getIDE().getConsole().write("doComparison() -> " + doComparison(), Console.MessageType.OUTPUT);

        if (doComparison()) {
            for (Block subBlock : getSubBlocks()) {
                subBlock.run();
            }
        }

        else {
            for (ElseIf elseIf : elseIfs) {
                if (elseIf.doComparison()) {
                    for (Block subBlock : elseIf.getSubBlocks()) {
                        subBlock.run();
                    }
                    return;
                }
            }

            if (elze != null) {
                for (Block subBlock : elze.getSubBlocks()) {
                    subBlock.run();
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " elseIfsSize=" + elseIfs.size() + " hasElse=" + (elze != null);
    }
}