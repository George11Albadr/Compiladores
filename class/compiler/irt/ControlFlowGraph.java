// Archivo: compiler/irt/ControlFlowGraph.java
package compiler.irt;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase que representa un grafo de flujo de control en el IRT.
 */
public class ControlFlowGraph {
    private BasicBlock entry;
    private BasicBlock exit;
    private Map<String, BasicBlock> blocks;

    /**
     * Constructor para ControlFlowGraph.
     */
    public ControlFlowGraph() {
        this.blocks = new HashMap<>();
    }

    /**
     * Establece el bloque de entrada del grafo.
     *
     * @param entry El bloque de entrada.
     */
    public void setEntry(BasicBlock entry) {
        if (entry == null) {
            throw new IllegalArgumentException("El bloque de entrada no puede ser nulo.");
        }
        this.entry = entry;
        blocks.put(entry.getLabel(), entry);
    }

    /**
     * Establece el bloque de salida del grafo.
     *
     * @param exit El bloque de salida.
     */
    public void setExit(BasicBlock exit) {
        if (exit == null) {
            throw new IllegalArgumentException("El bloque de salida no puede ser nulo.");
        }
        this.exit = exit;
        blocks.put(exit.getLabel(), exit);
    }

    /**
     * Añade un bloque al grafo.
     *
     * @param block El bloque a añadir.
     */
    public void addBlock(BasicBlock block) {
        if (block == null) {
            throw new IllegalArgumentException("El bloque no puede ser nulo.");
        }
        if (blocks.containsKey(block.getLabel())) {
            throw new IllegalArgumentException("El bloque con la etiqueta " + block.getLabel() + " ya existe.");
        }
        blocks.put(block.getLabel(), block);
    }

    /**
     * Elimina un bloque del grafo.
     *
     * @param label La etiqueta del bloque a eliminar.
     */
    public void removeBlock(String label) {
        if (!blocks.containsKey(label)) {
            throw new IllegalArgumentException("El bloque con la etiqueta " + label + " no existe.");
        }
        blocks.remove(label);
    }

    /**
     * Obtiene un bloque por su etiqueta.
     *
     * @param label La etiqueta del bloque.
     * @return El bloque correspondiente, o null si no existe.
     */
    public BasicBlock getBlock(String label) {
        return blocks.get(label);
    }

    /**
     * Obtiene el bloque de entrada.
     *
     * @return El bloque de entrada.
     */
    public BasicBlock getEntry() {
        return entry;
    }

    /**
     * Obtiene el bloque de salida.
     *
     * @return El bloque de salida.
     */
    public BasicBlock getExit() {
        return exit;
    }

    /**
     * Obtiene todos los bloques en el grafo.
     *
     * @return Una lista de todos los bloques básicos.
     */
    public List<BasicBlock> getBlocks() {
        return new ArrayList<>(blocks.values());
    }

    /**
     * Retorna una representación en cadena del grafo de flujo de control.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ControlFlowGraph:\n");
        for (BasicBlock block : blocks.values()) {
            sb.append(block.toString()).append("\n");
        }
        return sb.toString();
    }
}