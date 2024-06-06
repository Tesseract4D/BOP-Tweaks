package mods.tesseract.bopt.world.noise;

import java.util.Random;
import net.minecraft.world.gen.NoiseGenerator;

public class NoisePerlin extends NoiseGenerator {
    private int[] permutations;

    public double xCoord;

    public double yCoord;

    public double zCoord;

    public NoisePerlin() {
        this(new Random());
    }

    public NoisePerlin(Random random) {
        this.permutations = new int[512];
        this.xCoord = random.nextDouble() * 256.0D;
        this.yCoord = random.nextDouble() * 256.0D;
        this.zCoord = random.nextDouble() * 256.0D;
        for (int i = 0; i < 256; i++)
            this.permutations[i] = i;
        for (int j = 0; j < 256; j++) {
            int k = random.nextInt(256 - j) + j;
            int l = this.permutations[j];
            this.permutations[j] = this.permutations[k];
            this.permutations[k] = l;
            this.permutations[j + 256] = this.permutations[j];
        }
    }

    public double generateNoise(double d, double d1, double d2) {
        double d3 = d + this.xCoord;
        double d4 = d1 + this.yCoord;
        double d5 = d2 + this.zCoord;
        int i = (int)d3;
        int j = (int)d4;
        int k = (int)d5;
        if (d3 < i)
            i--;
        if (d4 < j)
            j--;
        if (d5 < k)
            k--;
        int l = i & 0xFF;
        int i1 = j & 0xFF;
        int j1 = k & 0xFF;
        d3 -= i;
        d4 -= j;
        d5 -= k;
        double d6 = d3 * d3 * d3 * (d3 * (d3 * 6.0D - 15.0D) + 10.0D);
        double d7 = d4 * d4 * d4 * (d4 * (d4 * 6.0D - 15.0D) + 10.0D);
        double d8 = d5 * d5 * d5 * (d5 * (d5 * 6.0D - 15.0D) + 10.0D);
        int k1 = this.permutations[l] + i1;
        int l1 = this.permutations[k1] + j1;
        int i2 = this.permutations[k1 + 1] + j1;
        int j2 = this.permutations[l + 1] + i1;
        int k2 = this.permutations[j2] + j1;
        int l2 = this.permutations[j2 + 1] + j1;
        return lerp(d8, lerp(d7, lerp(d6, grad(this.permutations[l1], d3, d4, d5), grad(this.permutations[k2], d3 - 1.0D, d4, d5)), lerp(d6, grad(this.permutations[i2], d3, d4 - 1.0D, d5), grad(this.permutations[l2], d3 - 1.0D, d4 - 1.0D, d5))), lerp(d7, lerp(d6, grad(this.permutations[l1 + 1], d3, d4, d5 - 1.0D), grad(this.permutations[k2 + 1], d3 - 1.0D, d4, d5 - 1.0D)), lerp(d6, grad(this.permutations[i2 + 1], d3, d4 - 1.0D, d5 - 1.0D), grad(this.permutations[l2 + 1], d3 - 1.0D, d4 - 1.0D, d5 - 1.0D))));
    }

    public final double lerp(double d, double d1, double d2) {
        return d1 + d * (d2 - d1);
    }

    public final double func_4110_a(int i, double d, double d1) {
        int j = i & 0xF;
        double d2 = (1 - ((j & 0x8) >> 3)) * d;
        double d3 = (j >= 4) ? ((j != 12 && j != 14) ? d1 : d) : 0.0D;
        return (((j & 0x1) != 0) ? -d2 : d2) + (((j & 0x2) != 0) ? -d3 : d3);
    }

    public final double grad(int i, double d, double d1, double d2) {
        int j = i & 0xF;
        double d3 = (j >= 8) ? d1 : d;
        double d4 = (j >= 4) ? ((j != 12 && j != 14) ? d2 : d) : d1;
        return (((j & 0x1) != 0) ? -d3 : d3) + (((j & 0x2) != 0) ? -d4 : d4);
    }

    public double func_801_a(double d, double d1) {
        return generateNoise(d, d1, 0.0D);
    }

    public void func_805_a(double[] ad, double d, double d1, double d2, int i, int j, int k, double d3, double d4, double d5, double d6) {
        if (j == 1) {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            double d8 = 0.0D;
            double d10 = 0.0D;
            int j3 = 0;
            double d12 = 1.0D / d6;
            for (int i4 = 0; i4 < i; i4++) {
                double d14 = (d + i4) * d3 + this.xCoord;
                int j4 = (int)d14;
                if (d14 < j4)
                    j4--;
                int k4 = j4 & 0xFF;
                d14 -= j4;
                double d17 = d14 * d14 * d14 * (d14 * (d14 * 6.0D - 15.0D) + 10.0D);
                for (int l4 = 0; l4 < k; l4++) {
                    double d19 = (d2 + l4) * d5 + this.zCoord;
                    int j5 = (int)d19;
                    if (d19 < j5)
                        j5--;
                    int l5 = j5 & 0xFF;
                    d19 -= j5;
                    double d21 = d19 * d19 * d19 * (d19 * (d19 * 6.0D - 15.0D) + 10.0D);
                    int l = this.permutations[k4] + 0;
                    int j1 = this.permutations[l] + l5;
                    int k1 = this.permutations[k4 + 1] + 0;
                    int l1 = this.permutations[k1] + l5;
                    double d9 = lerp(d17, func_4110_a(this.permutations[j1], d14, d19), grad(this.permutations[l1], d14 - 1.0D, 0.0D, d19));
                    double d11 = lerp(d17, grad(this.permutations[j1 + 1], d14, 0.0D, d19 - 1.0D), grad(this.permutations[l1 + 1], d14 - 1.0D, 0.0D, d19 - 1.0D));
                    double d23 = lerp(d21, d9, d11);
                    ad[j3++] = ad[j3++] + d23 * d12;
                }
            }
            return;
        }
        int i1 = 0;
        double d7 = 1.0D / d6;
        int i2 = -1;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag9 = false;
        double d13 = 0.0D;
        double d15 = 0.0D;
        double d16 = 0.0D;
        double d18 = 0.0D;
        for (int i5 = 0; i5 < i; i5++) {
            double d20 = (d + i5) * d3 + this.xCoord;
            int k5 = (int)d20;
            if (d20 < k5)
                k5--;
            int i6 = k5 & 0xFF;
            d20 -= k5;
            double d22 = d20 * d20 * d20 * (d20 * (d20 * 6.0D - 15.0D) + 10.0D);
            for (int j6 = 0; j6 < k; j6++) {
                double d24 = (d2 + j6) * d5 + this.zCoord;
                int k6 = (int)d24;
                if (d24 < k6)
                    k6--;
                int l6 = k6 & 0xFF;
                d24 -= k6;
                double d25 = d24 * d24 * d24 * (d24 * (d24 * 6.0D - 15.0D) + 10.0D);
                for (int i7 = 0; i7 < j; i7++) {
                    double d26 = (d1 + i7) * d4 + this.yCoord;
                    int j7 = (int)d26;
                    if (d26 < j7)
                        j7--;
                    int k7 = j7 & 0xFF;
                    d26 -= j7;
                    double d27 = d26 * d26 * d26 * (d26 * (d26 * 6.0D - 15.0D) + 10.0D);
                    if (i7 == 0 || k7 != i2) {
                        i2 = k7;
                        int j2 = this.permutations[i6] + k7;
                        int k2 = this.permutations[j2] + l6;
                        int l2 = this.permutations[j2 + 1] + l6;
                        int i3 = this.permutations[i6 + 1] + k7;
                        int k3 = this.permutations[i3] + l6;
                        int l3 = this.permutations[i3 + 1] + l6;
                        d13 = lerp(d22, grad(this.permutations[k2], d20, d26, d24), grad(this.permutations[k3], d20 - 1.0D, d26, d24));
                        d15 = lerp(d22, grad(this.permutations[l2], d20, d26 - 1.0D, d24), grad(this.permutations[l3], d20 - 1.0D, d26 - 1.0D, d24));
                        d16 = lerp(d22, grad(this.permutations[k2 + 1], d20, d26, d24 - 1.0D), grad(this.permutations[k3 + 1], d20 - 1.0D, d26, d24 - 1.0D));
                        d18 = lerp(d22, grad(this.permutations[l2 + 1], d20, d26 - 1.0D, d24 - 1.0D), grad(this.permutations[l3 + 1], d20 - 1.0D, d26 - 1.0D, d24 - 1.0D));
                    }
                    double d28 = lerp(d27, d13, d15);
                    double d29 = lerp(d27, d16, d18);
                    double d30 = lerp(d25, d28, d29);
                    ad[i1++] = ad[i1++] + d30 * d7;
                }
            }
        }
    }
}
