"""
CS361 Project 2 — Priority Queue (Min-Heap) Implementation of Dijkstra's Algorithm
Author: [Your Name]
"""

import heapq
import time
import tracemalloc
import random
from collections import defaultdict


# =============================================================================
# GRAPH REPRESENTATION
# =============================================================================

def build_adjacency_list(edges, directed=False):
    """Build adjacency list from edge list."""
    adj = defaultdict(list)
    for u, v, w in edges:
        adj[u].append((v, w))
        if not directed:
            adj[v].append((u, w))
    return adj


# =============================================================================
# DIJKSTRA — PRIORITY QUEUE (MIN-HEAP)
# Expected time complexity: O((V + E) log V)
# =============================================================================

def dijkstra_heap(adj, source, vertices):
    """
    Heap-based Dijkstra using an adjacency list and a min-priority queue.

    Key idea: always process the globally unvisited vertex with the smallest
    known tentative distance first. A binary min-heap gives us that in O(log V)
    instead of the O(V) linear scan used by the array version.

    Time complexity : O((V + E) log V)
    Space complexity: O(V + E)

    Returns
    -------
    dist   : dict  {vertex -> shortest distance from source}
    parent : dict  {vertex -> predecessor on shortest path}
    """
    dist   = {v: float('inf') for v in vertices}
    parent = {v: None         for v in vertices}
    dist[source] = 0

    # heap entries: (tentative_distance, vertex)
    heap = [(0, source)]

    while heap:
        d, u = heapq.heappop(heap)

        # Stale entry — a shorter path to u was already found
        if d > dist[u]:
            continue

        for neighbor, weight in adj[u]:
            new_dist = d + weight
            if new_dist < dist[neighbor]:           # relaxation
                dist[neighbor] = new_dist
                parent[neighbor] = u
                heapq.heappush(heap, (new_dist, neighbor))

    return dist, parent


# =============================================================================
# PATH RECONSTRUCTION  (Extra Credit)
# =============================================================================

def reconstruct_path(parent, source, target):
    """Trace parent pointers from target back to source."""
    path, node = [], target
    while node is not None:
        path.append(node)
        node = parent[node]
    path.reverse()
    return path if path and path[0] == source else []


# =============================================================================
# PRINTING HELPERS
# =============================================================================

def print_results(dist, parent, source, show_paths=True):
    print(f"\n  Shortest distances from source '{source}':")
    for v in sorted(dist.keys(), key=str):
        d = dist[v]
        d_str = str(d) if d != float('inf') else "∞"
        if show_paths:
            path = reconstruct_path(parent, source, v)
            path_str = " → ".join(str(x) for x in path) if path else "unreachable"
            print(f"    {v}: {d_str:>8}    path: {path_str}")
        else:
            print(f"    {v}: {d_str:>8}")


# =============================================================================
# TIMING + MEMORY MEASUREMENT
# =============================================================================

def measure(fn, *args, trials=5):
    """Run fn(*args) `trials` times. Returns (avg_ms, peak_mb, last_result)."""
    times, peak_mem, result = [], 0, None
    for _ in range(trials):
        tracemalloc.start()
        t0     = time.perf_counter()
        result = fn(*args)
        t1     = time.perf_counter()
        _, peak = tracemalloc.get_traced_memory()
        tracemalloc.stop()
        times.append((t1 - t0) * 1000)
        peak_mem = max(peak_mem, peak)
    return sum(times) / len(times), peak_mem / (1024 * 1024), result


# =============================================================================
# PROVIDED EXAMPLE GRAPHS
# =============================================================================

SPARSE_GRAPH_1 = {
    "name": "Sparse-1",
    "vertices": ["A", "B", "C", "D", "E", "F"],
    "edges":    [("A","B",4),("A","C",2),("B","D",5),("C","D",1),("D","E",3),("E","F",2)],
    "source":   "A",
}

SPARSE_GRAPH_2 = {
    "name": "Sparse-2",
    "vertices": [1,2,3,4,5,6,7],
    "edges":    [(1,2,3),(1,3,6),(2,4,2),(3,5,4),(4,6,7),(5,7,1),(2,5,5)],
    "source":   1,
}

DENSE_GRAPH_1 = {
    "name": "Dense-1",
    "vertices": ["A","B","C","D","E"],
    "edges":    [("A","B",2),("A","C",5),("A","D",1),("A","E",4),
                 ("B","C",3),("B","D",2),("B","E",6),
                 ("C","D",3),("C","E",1),("D","E",2)],
    "source":   "A",
}

DENSE_GRAPH_2 = {
    "name": "Dense-2",
    "vertices": [1,2,3,4,5,6],
    "edges":    [(1,2,3),(1,3,2),(1,4,6),(1,5,5),(1,6,4),
                 (2,3,1),(2,4,2),(2,5,4),(2,6,7),
                 (3,4,3),(3,5,6),(3,6,5),
                 (4,5,2),(4,6,4),(5,6,1)],
    "source":   1,
}


# =============================================================================
# RANDOM GRAPH GENERATORS
# =============================================================================

def generate_sparse_graph(V, density_factor=2):
    """E ≈ density_factor * V (connected via random spanning tree + extras)."""
    verts = list(range(V))
    random.shuffle(verts)
    edges = set()
    for i in range(1, V):
        u, v = verts[i-1], verts[i]
        edges.add((min(u,v), max(u,v), random.randint(1, 20)))
    target = density_factor * V
    attempts = 0
    while len(edges) < target and attempts < target * 10:
        u, v = random.randint(0, V-1), random.randint(0, V-1)
        if u != v:
            edges.add((min(u,v), max(u,v), random.randint(1, 20)))
        attempts += 1
    return list(range(V)), list(edges)


def generate_dense_graph(V, density=0.7):
    """E ≈ density * V*(V-1)/2."""
    edges = []
    for u in range(V):
        for v in range(u+1, V):
            if random.random() < density:
                edges.append((u, v, random.randint(1, 20)))
    return list(range(V)), edges


# =============================================================================
# RUN ONE GRAPH
# =============================================================================

def run_graph(g, show_paths=True, trials=5):
    vertices, edges, source = g["vertices"], g["edges"], g["source"]
    V, E = len(vertices), len(edges)
    adj  = build_adjacency_list(edges)

    print(f"\n{'='*60}")
    print(f"  {g['name']}   V={V}  E={E}  source={source}")
    print(f"{'='*60}")

    avg_ms, peak_mb, (dist, parent) = measure(
        dijkstra_heap, adj, source, vertices, trials=trials)

    print_results(dist, parent, source, show_paths)
    print(f"\n  Time : {avg_ms:.4f} ms (avg of {trials} trials)")
    print(f"  Mem  : {peak_mb:.6f} MB")

    return {"name": g["name"], "V": V, "E": E,
            "time_ms": round(avg_ms, 4), "mem_mb": round(peak_mb, 6),
            "dist": dist, "parent": parent}


# =============================================================================
# LARGE-SCALE EXPERIMENTS
# =============================================================================

def run_experiments():
    print("\n\n" + "="*60)
    print("  LARGE-SCALE TIMING EXPERIMENTS")
    print("="*60)

    configs = [
        ("Sparse",  100,  True),
        ("Sparse",  500,  True),
        ("Sparse", 1000,  True),
        ("Sparse", 2000,  True),
        ("Sparse", 5000,  True),
        ("Dense",   100, False),
        ("Dense",   500, False),
        ("Dense",  1000, False),
        ("Dense",  2000, False),
    ]

    rows = []
    for label, V, is_sparse in configs:
        if is_sparse:
            verts, edges = generate_sparse_graph(V, density_factor=2)
        else:
            density = 0.5 if V <= 500 else 0.1
            verts, edges = generate_dense_graph(V, density=density)

        E   = len(edges)
        adj = build_adjacency_list(edges)
        avg_ms, peak_mb, _ = measure(dijkstra_heap, adj, 0, verts, trials=3)

        rows.append({"name": f"{label} V={V}", "V": V, "E": E,
                     "time_ms": round(avg_ms, 3), "mem_mb": round(peak_mb, 4)})
        print(f"  {label:6s} V={V:5d}  E={E:7d} | {avg_ms:8.3f} ms  {peak_mb:.4f} MB")

    return rows


# =============================================================================
# SUMMARY TABLE
# =============================================================================

def print_table(rows):
    print("\n" + "="*65)
    print(f"  {'Graph':<20} {'V':>5} {'E':>8} {'Time(ms)':>12} {'Mem(MB)':>10}")
    print("  " + "-"*61)
    for r in rows:
        print(f"  {r['name']:<20} {r['V']:>5} {r['E']:>8} {r['time_ms']:>12.4f} {r['mem_mb']:>10.6f}")
    print("="*65)


# =============================================================================
# MAIN
# =============================================================================

if __name__ == "__main__":
    print("CS361 Project 2 — Dijkstra: Priority Queue Implementation")
    print("Path reconstruction enabled (extra credit)\n")

    # Dense-3: required third dense graph (complete K8)
    random.seed(42)
    v3, e3 = generate_dense_graph(8, density=1.0)
    DENSE_GRAPH_3 = {"name": "Dense-3 (K8)", "vertices": v3, "edges": e3, "source": 0}

    example_results = []
    for g in [SPARSE_GRAPH_1, SPARSE_GRAPH_2, DENSE_GRAPH_1, DENSE_GRAPH_2, DENSE_GRAPH_3]:
        row = run_graph(g, show_paths=True)
        example_results.append(row)

    print("\n\n--- Example Graphs Summary ---")
    print_table(example_results)

    large_rows = run_experiments()
    print("\n--- Large-Scale Experiments Summary ---")
    print_table(large_rows)