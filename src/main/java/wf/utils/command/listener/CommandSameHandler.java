package wf.utils.command.listener;

import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.SubCommand;

import java.util.Map;

public class CommandSameHandler<T> extends AbstractCommandHandler<T> {


    private final double maxSimilarityPercent;

    public CommandSameHandler() {
        maxSimilarityPercent = 0.75;
    }

    public CommandSameHandler(double maxSimilarityPercent) {
        this.maxSimilarityPercent = maxSimilarityPercent;
    }



    @Override
    public boolean onCommand(String input, CommandSender sender, T t) {
        if (input == null || input.isBlank()) return false;

        String[] args = input.trim().split("\\s+");

        SubCommand<T> bestMatch = null;
        double bestSimilarity = 0.0;
        int bestArgLength = 0;

        for (Map.Entry<String, SubCommand<T>> entry : subcommands.entrySet()) {
            String[] subcommandArgs = entry.getKey().split("\\.");

            if (args.length < subcommandArgs.length) continue;

            boolean allSimilar = true;
            double totalSimilarity = 0.0;

            for (int i = 0; i < subcommandArgs.length; i++) {
                double similarity = similarityPercent(args[i], subcommandArgs[i]);
                if (similarity < 0.75) {
                    allSimilar = false;
                    break;
                }
                totalSimilarity += similarity;
            }

            if (allSimilar) {
                double avgSimilarity = totalSimilarity / subcommandArgs.length;
                if (avgSimilarity > bestSimilarity) {
                    bestSimilarity = avgSimilarity;
                    bestMatch = entry.getValue();
                    bestArgLength = subcommandArgs.length;
                }
            }
        }

        if (bestMatch != null) {
            bestMatch.onCommand(sender, t, args, bestArgLength);
            return true;
        }

        sender.sendMessage("Command not found!");
        return false;
    }

    private double similarityPercent(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        if (maxLen == 0) return 1.0; // оба пустые строки

        int distance = levenshteinDistance(a.toLowerCase(), b.toLowerCase());
        return 1.0 - ((double) distance / maxLen);
    }

    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
