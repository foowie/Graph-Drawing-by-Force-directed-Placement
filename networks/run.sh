#!/bin/bash
java -jar ../dist/FruchtermanReingold.jar -i=K/K4.in -w=500 -h=500 -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=K/K4.in -o=K/K4.in-grid -w=500 -h=500 -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=K/K5.in -w=500 -h=500 -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=K/K5.in -o=K/K5.in-grid -w=500 -h=500 -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=K/K6.in -w=500 -h=500 -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=K/K6.in -o=K/K6.in-grid -w=500 -h=500 -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=K/K7.in -w=500 -h=500 -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=K/K7.in -o=K/K7.in-grid -w=500 -h=500 -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=K/K8.in -w=500 -h=500 -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=K/K8.in -o=K/K8.in-grid -w=500 -h=500 -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=4tree.in -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=4tree.in -o=4tree.in-grid -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=cube.in -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=cube.in -o=cube.in-grid -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=circle.in -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=circle.in -o=circle.in-grid -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=random.in -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=random.in -o=random.in-grid -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=social1Inter_st.in -t=1000 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=social1Inter_st.in -o=social1Inter_st.in-grid -t=1000 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=celegans_n306.in -n=no -w=2000 -h=2000 -t=200 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=celegans_n306.in -o=celegans_n306.in-grid -n=no -w=2000 -h=2000 -t=200 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=USairport500.in -n=no -w=2000 -h=2000 -t=200 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=USairport500.in -o=USairport500.in-grid -n=no -w=2000 -h=2000 -t=200 -s=yes

java -jar ../dist/FruchtermanReingold.jar -i=yeastInter_st.in -n=no -w=2000 -h=2000 -t=200 -s=yes -g=no
java -jar ../dist/FruchtermanReingold.jar -i=yeastInter_st.in -o=yeastInter_st.in-grid -n=no -w=2000 -h=2000 -t=200 -s=yes


