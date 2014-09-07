package controllers;

import com.google.inject.Inject;
import models.FrutasBag;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import models.Fruta;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by charly on 9/2/14.
 */
public class FrutaController {

    @Inject
    FrutasBag bag;

    public Result getAllFrutas(){

        List<Fruta> frutas = new ArrayList<Fruta>();
        frutas.add(new Fruta("banana", "yelow"));
        frutas.add(new Fruta("apple", "red"));
        return Results.json().render(frutas);
    }

    public Result postAFruta(@Param("name") String name,
                             @Param("color") String color,
                             Context context){
        return Results.json().render("DNI DE LA BOLSA DE FRUTA  "
                + bag.hashCode()
                + "\n with name: " + name
                + "\n and color: " + color);
    }
}