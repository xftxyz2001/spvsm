import os

with open('code.md', 'w', encoding='utf-8') as f:
    for dirpath, dirnames, filenames in os.walk('.'):
        for filename in filenames:
            if filename.endswith('.java'):
                f.write(filename + '\n')
                f.write('```java\n')
                with open(os.path.join(dirpath, filename), 'r', encoding='utf-8') as f2:
                    f.write(str(f2.read()))
                f.write('```\n')
                f.write('\n')
