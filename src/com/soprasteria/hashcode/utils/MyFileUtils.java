package com.soprasteria.hashcode.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

public class MyFileUtils
{

    public static Collection<Collection<String>> readLineWithSeparator(File file, String separator)
    {
        Collection<Collection<String>> result = new ArrayList<Collection<String>>();

        try
        {
            List<String> lines = FileUtils.readLines(file);

            for (String line : lines)
            {
                List<String> lineResult = new ArrayList<String>();
                StringTokenizer tokenizer = new StringTokenizer(line, separator);
                while (tokenizer.hasMoreTokens())
                {
                    lineResult.add(tokenizer.nextToken());
                }
                result.add(lineResult);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static int[][] readMatrix(File file, String separator)
    {
        int[][] result;
        boolean first = true;
        int lineSize = 0;

        try
        {
            List<String> lines = FileUtils.readLines(file);
            List<List<Integer>> readedLines = new ArrayList<List<Integer>>();

            for (String line : lines)
            {
                List<Integer> lineResult = new ArrayList<Integer>();
                StringTokenizer tokenizer = new StringTokenizer(line, separator, false);

                while (tokenizer.hasMoreTokens())
                {
                    lineResult.add(Integer.valueOf(tokenizer.nextToken()));
                    if (first)
                    {
                        lineSize++;
                    }
                }

                readedLines.add(lineResult);
                first = false;
            }

            result = new int[readedLines.size()][lineSize];

            int x = 0;
            int y;
            for (List<Integer> xLine : readedLines)
            {
                y = 0;
                for (Integer yLine : xLine)
                {
                    result[x][y] = yLine;
                    y++;
                }
                x++;
            }
        }
        catch (IOException e)
        {
            result = new int[0][0];
            e.printStackTrace();
        }

        return result;
    }

    public static <T> T[][] readMatrix(File file, String separator, Class<T> clazz, Converter<T> converter)
    {
        T[][] result;
        boolean first = true;
        int lineSize = 0;

        try
        {
            List<String> lines = FileUtils.readLines(file);
            List<List<T>> readedLines = new ArrayList<List<T>>();

            for (String line : lines)
            {
                List<T> lineResult = new ArrayList<T>();
                StringTokenizer tokenizer = new StringTokenizer(line, separator, false);

                while (tokenizer.hasMoreTokens())
                {
                    lineResult.add(converter.convertTo(tokenizer.nextToken()));
                    if (first)
                    {
                        lineSize++;
                    }
                }

                readedLines.add(lineResult);
                first = false;
            }

            result = (T[][]) Array.newInstance(clazz, readedLines.size(), lineSize);

            int x = 0;
            int y;
            for (List<T> xLine : readedLines)
            {
                y = 0;
                for (T yLine : xLine)
                {
                    result[x][y] = yLine;
                    y++;
                }
                x++;
            }
        }
        catch (IOException e)
        {
            result = (T[][]) Array.newInstance(clazz, 0, 0);
            e.printStackTrace();
        }

        return result;
    }

    public static void displayMatrix(int[][] m)
    {
        for (int[] x : m)
        {
            for (int y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public static <T> void displayMatrix(T[][] m)
    {
        for (T[] x : m)
        {
            for (T y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        System.out.println(readLineWithSeparator(new File("C:\\java\\battle.txt"), ";"));

        int[][] result = readMatrix(new File("C:\\java\\battle_matrix.txt"), ",[] ");
        displayMatrix(result);

        Converter<String> c = new Converter<String>()
        {
            @Override
            public String convertTo(String token)
            {
                return token;
            }
        };

        String[][] resultString = readMatrix(new File("C:\\java\\battle_matrix.txt"), ",[] ", String.class, c);
        displayMatrix(result);
    }
}
