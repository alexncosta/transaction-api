package com.educacaovirtual.transactionapi.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private BigDecimal total;
    private LocalDateTime createdAt;
    private String cardNumber;
    private String ccv;
    private String owner;
    private String ein;

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCcv() {
        return ccv;
    }

    public String getOwner() {
        return owner;
    }

    public String getEin() {
        return ein;
    }

    public TransactionDTO() {
    }

    public TransactionDTO(BigDecimal total, LocalDateTime createdAt, String cardNumber, String ccv, String owner, String ein) {
        this.total = total;
        this.createdAt = createdAt;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.owner = owner;
        this.ein = ein;
    }

    public TransactionDTO(Builder builder) {
        this.total = builder.total;
        this.createdAt = builder.createdAt;
        this.cardNumber = builder.cardNumber;
        this.ccv = builder.ccv;
        this.owner = builder.owner;
        this.ein = builder.ein;
    }

    public static class Builder {
        private BigDecimal total;
        private LocalDateTime createdAt;
        private String cardNumber;
        private String ccv;
        private String owner;
        private String ein;

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder ccv(String ccv) {
            this.ccv = ccv;
            return this;
        }

        public Builder owner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder ein(String ein) {
            this.ein = ein;
            return this;
        }

        public TransactionDTO build() {
            return new TransactionDTO(this);
        }
    }
}
