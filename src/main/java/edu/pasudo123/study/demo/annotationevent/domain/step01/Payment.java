package edu.pasudo123.study.demo.annotationevent.domain.step01;

/**
 * Payment <-- PaymentAsync
 * Payment <-- PaymentSync  가 각각 구현하고 잇다.
 */
public interface Payment {

    void printCurrentPayInfo();
}
