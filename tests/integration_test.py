import subprocess
import sys

def run_java_engine(customer_type, promo_code, items):
    # Construct command
    # Replace 'gradle run --args=' or 'java -cp ...' as needed
    # For simplicity, we'll try to run via java -cp if classes are compiled
    # But usually in labs, we'd use gradle.
    
    cmd = [
        "java", "-cp", "build/classes/java/main", 
        "com.pricing.PricingApp", customer_type, promo_code
    ]
    for price, qty in items:
        cmd.append(f"{price}:{qty}")

    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Error: {result.stderr}")
        return None

    # Parse key-value output
    output = {}
    for line in result.stdout.strip().split("\n"):
        if "=" in line:
            k, v = line.split("=")
            output[k] = float(v)
    return output

def test_scenario_1():
    print("Running Scenario 1: Regular customer, no promo, $30 order...")
    results = run_java_engine("REGULAR", "none", [(10.0, 1), (20.0, 1)])
    if results:
        assert results["total"] == 36.0
        print("Scenario 1 Passed!")

def test_scenario_2():
    print("Running Scenario 2: VIP customer, SAVE10 promo, $100 order...")
    results = run_java_engine("VIP", "SAVE10", [(100.0, 1)])
    if results:
        assert results["total"] == 90.0
        print("Scenario 2 Passed!")

if __name__ == "__main__":
    test_scenario_1()
    test_scenario_2()
