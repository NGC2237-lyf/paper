package com.example.papercut.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TransparentBackgroundUtil {
    //inputImagePath为输入的图片地址 outputImagePath为返回的图片地址
    public static void makeTransparent(String inputImagePath, String outputImagePath) throws IOException {
        // 检查输入文件是否存在且可读
        File inputFile = new File(inputImagePath);
        if (!inputFile.exists() || !inputFile.canRead()) {
            System.err.println("错误: 输入文件不存在或不可读。");
            return;
        }

        // 读取图片
        BufferedImage image = ImageIO.read(inputFile);

        // 分离背景并设置为透明
        BufferedImage result = makeBackgroundTransparent(image);

        // 保存处理后的图片
        ImageIO.write(result, "PNG", new File(outputImagePath));
    }

    public static BufferedImage makeBackgroundTransparent(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // 设置颜色阈值，这里假设背景是白色
        int whiteRgb = Color.WHITE.getRGB();
        int tolerance = 100; // 容差值，表示颜色的相似度
        int transparentArgb = 0x00FFFFFF; // 完全透明的ARGB值

        // 分离背景并设置为透明
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                if (isSimilarColor(rgb, whiteRgb, tolerance)) {
                    result.setRGB(x, y, transparentArgb); // 将背景设置为透明
                } else {
                    result.setRGB(x, y, rgb); // 其他部分保持不变
                }
            }
        }

        return result;
    }

    public static boolean isSimilarColor(int color1, int color2, int tolerance) {
        int red1 = (color1 >> 16) & 0xFF;
        int green1 = (color1 >> 8) &  0xFF;
        int blue1 = color1 & 0xFF;

        int red2 = (color2 >> 16) & 0xFF;
        int green2 = (color2 >> 8) & 0xFF;
        int blue2 = color2 & 0xFF;

        int diffRed = Math.abs(red1 - red2);
        int diffGreen = Math.abs(green1 - green2);
        int diffBlue = Math.abs(blue1 - blue2);

        return (diffRed <= tolerance && diffGreen <= tolerance && diffBlue <= tolerance);
    }
}

