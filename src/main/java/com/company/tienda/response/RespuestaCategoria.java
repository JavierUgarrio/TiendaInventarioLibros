package com.company.tienda.response;

import java.util.*;
import com.company.tienda.modelo.Categoria;
import lombok.Data;

@Data
public class RespuestaCategoria {

	private List<Categoria> categoria;
}
