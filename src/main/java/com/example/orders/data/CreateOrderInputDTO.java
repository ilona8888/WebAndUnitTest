package com.example.orders.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderInputDTO {
    @NonNull
    private String label;

    @NonNull
    private String target;

    @NonNull
    private String provider;

    @NonNull
    private String count;
}
