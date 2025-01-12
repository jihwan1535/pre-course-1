package calculator.learn;

import calculator.domain.Separator;

class SpeedTest {

    //@Test
    void compareSeparateAndDivide() {
        String data = "1,2:3,".repeat(10000);
        double separateTime = separateTime(data);
        double splitTime = splitTime(data);
        System.out.println("data length: " + data.length());
        System.out.println("separateTime: " + separateTime + "ms");
        System.out.println("splitTime: " + splitTime + "ms");
    }

    private double separateTime(String data) {
        Separator separator = new Separator();
        long start = System.nanoTime();
        separator.separate(data);
        long end = System.nanoTime();

        return (end - start) / 1_000_000.0;
    }

    private double splitTime(String data) {
        long start = System.nanoTime();
        data.split("[:,]");
        long end = System.nanoTime();

        return (end - start) / 1_000_000.0;
    }

}
