package cz.hronza.rhrpoc.business_logic.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface OperationService {
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
