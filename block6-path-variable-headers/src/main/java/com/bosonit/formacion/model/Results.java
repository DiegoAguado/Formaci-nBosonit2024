package com.bosonit.formacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Results {
    List<String> headers;
    List<String> params;
    String body;
}
