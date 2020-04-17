package ru.sibsutis;
/*
!!!WARNING!!!
!!!SHITCODE AHEAD!!!
Because I'm too stupid to connect KPI to User instead of connecting it to Developer and Manager :C
 */
public class StatisticKPI <T> {
    public T maximum;
    public T minimum;

    @SafeVarargs
    public final T getMaxKPI (T... varargs) {
        maximum = varargs[0];
        if (maximum instanceof Manager) {
            System.out.println("Using Manager");
            for (T vararg: varargs) {
                if (((Manager) maximum).getKPI() < ((Manager) vararg).getKPI())
                    maximum = vararg;
            }
        } else if (maximum instanceof Developer) {
            System.out.println("Using Developer");
            for (T vararg: varargs) {
                if (((Developer) maximum).getKPI() < ((Developer) vararg).getKPI())
                    maximum = vararg;
            }
        }
        return maximum;
    }

    @SafeVarargs
    public final T getMinKPI (T... varargs) {
        minimum = varargs[0];
        if (minimum instanceof Manager) {
            System.out.println("Using Manager");
            for (T vararg: varargs) {
                if (((Manager) minimum).getKPI() > ((Manager) vararg).getKPI())
                    minimum = vararg;
            }
        } else if (minimum instanceof Developer) {
            System.out.println("Using Developer");
            for (T vararg: varargs) {
                if (((Developer) minimum).getKPI() > ((Developer) vararg).getKPI())
                    minimum = vararg;
            }
        }
        return minimum;
    }

    @SafeVarargs
    public final T getAvgKPI (T... varargs) {
        Integer avg = 0;
        Integer distance;
        Integer nearestDistance;
        Integer nearestIndex = 0;
        if (varargs[0] instanceof Developer) {
            for (T varag : varargs)
                avg += ((Developer) varag).getKPI();
            avg = avg / varargs.length;
            nearestDistance = Math.abs(((Developer)varargs[0]).getKPI() - avg);
            nearestIndex = 0;
            for(int i = 1; i < varargs.length; i++) {
                T varag = varargs[i];
                distance = Math.abs(((Developer)varag).getKPI() - avg);
                if (distance < nearestDistance) {
                    nearestIndex = i;
                    nearestDistance = distance;
                }
            }
        }
        else if (varargs[0] instanceof Manager) {
            for (T varag : varargs)
                avg += ((Manager) varag).getKPI();
            avg = avg / varargs.length;
            nearestDistance = Math.abs(((Manager)varargs[0]).getKPI() - avg);
            nearestIndex = 0;
            for(int i = 1; i < varargs.length; i++) {
                T varag = varargs[i];
                distance = Math.abs(((Manager)varag).getKPI() - avg);
                if (distance < nearestDistance) {
                    nearestIndex = i;
                    nearestDistance = distance;
                }
            }
        }
        return varargs[nearestIndex];
    }
}
