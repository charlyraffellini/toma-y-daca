package models;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charly on 9/2/14.
 */

@Singleton
public class FrutasBag {
    public List<Fruta> frutas;

    public FrutasBag(){
        this.frutas = new ArrayList<Fruta>();
    }
}

