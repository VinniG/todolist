package br.com.viniciusgomes.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    // Este método copia as propriedades não nulas do objeto de origem para o objeto de destino.
    public static void copyNonNullProperties(Object source, Object target) {

        // Utiliza a classe BeanUtils da biblioteca Spring para realizar a cópia das propriedades.
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Este método retorna um array de strings contendo os nomes das propriedades nulas no objeto de origem.
    public static String[] getNullPropertyNames(Object source) {
        
        // Cria um BeanWrapper para o objeto de origem usando a biblioteca Spring.
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Obtém todas as propriedades do objeto de origem.
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Cria um conjunto para armazenar os nomes das propriedades nulas.
        Set<String> emptyNames = new HashSet<>();

        // Itera pelas propriedades do objeto de origem.
        for (PropertyDescriptor pd : pds) {
            // Obtém o valor da propriedade.
            Object srcValue = src.getPropertyValue(pd.getName());
            
            // Se o valor da propriedade for nulo, adiciona o nome da propriedade ao conjunto.
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        // Converte o conjunto em um array de strings e o retorna.
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
