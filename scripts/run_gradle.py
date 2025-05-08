import os
import subprocess
import sys


def run_program(program: str | os.PathLike, arguments: list[str] = []) -> int:
    result = subprocess.run([program] + arguments)
    return result.returncode

def main():
    if len (sys.argv) < 2:
        print("Usage: python run_gradle.py <task>")
        sys.exit(1)
    else:
        gradlew = "gradlew.bat" if os.name == "nt" else "gradlew"
        gradlew_path = os.path.join(os.getcwd(), gradlew)

        run_program(gradlew_path, ["spotlessApply"])
        return_code = run_program(gradlew_path, sys.argv[1:])

        sys.exit(return_code)

if __name__ == "__main__":
    main()
