package cz.hronza.business_logic;

import javax.validation.constraints.NotNull;

public interface Operation {
    /**
     *
     * @param a   int number
     * @param b   int number
     * @return   a + b
     */
    int sum(@NotNull Integer a, @NotNull Integer b);

    /**
     *
     * @param a   int number
     * @param b   int number
     * @return   a - b
     */
    int difference(@NotNull Integer a, @NotNull Integer b);

    /**
     *
     * @param a   int number
     * @param b   int number
     * @return   a / b
     */
    int divide(@NotNull Integer a, @NotNull Integer b);

    /**
     *
     * @param a   int number
     * @param b   int number
     * @return   a * b
     */
    int multiplication(@NotNull Integer a, @NotNull Integer b);
}
