document.addEventListener("DOMContentLoaded", () => {
    const hero = document.querySelector(".hero");

    // ===== CANVAS PARTICLES =====
    const canvas = document.createElement("canvas");
    const ctx = canvas.getContext("2d");
    canvas.style.position = "absolute";
    canvas.style.top = 0; canvas.style.left = 0;
    canvas.style.width = "100%"; canvas.style.height = "100%";
    canvas.style.pointerEvents = "none"; canvas.style.zIndex = 0;
    hero.prepend(canvas);
    hero.style.position = "relative";

    let width, height;
    function resize() { width = canvas.width = hero.offsetWidth; height = canvas.height = hero.offsetHeight; }
    window.addEventListener("resize", resize); resize();

    const mouse = { x: width/2, y: height/2 };
    hero.addEventListener("mousemove", e => {
        const rect = hero.getBoundingClientRect();
        mouse.x = e.clientX - rect.left;
        mouse.y = e.clientY - rect.top;
    });

    const orbCount = 30;
    const orbs = Array.from({length: orbCount}, () => ({
        x: Math.random()*width, y: Math.random()*height,
        r: Math.random()*0.5+0.5,
        dx:(Math.random()-0.5)*0.2, dy:(Math.random()-0.5)*0.2,
        color:"rgba(0,120,0,0.85)"
    }));

    function updateOrbs(){
        ctx.clearRect(0,0,width,height);
        orbs.forEach((orb,i)=>{
            orb.x+=orb.dx; orb.y+=orb.dy;
            if(orb.x<0||orb.x>width) orb.dx*=-1;
            if(orb.y<0||orb.y>height) orb.dy*=-1;

            const dx = orb.x - mouse.x, dy = orb.y - mouse.y;
            const dist = Math.sqrt(dx*dx + dy*dy);
            if(dist<100){ const force = (100-dist)/100*0.1; orb.dx+=(dx/dist)*force; orb.dy+=(dy/dist)*force; }

            ctx.beginPath(); ctx.arc(orb.x, orb.y, orb.r*14, 0, Math.PI*2);
            ctx.fillStyle = orb.color; ctx.fill();

            for(let j=i+1;j<orbs.length;j++){
                const dx2=orb.x-orbs[j].x, dy2=orb.y-orbs[j].y, d=Math.sqrt(dx2*dx2+dy2*dy2);
                if(d<120){ ctx.beginPath(); ctx.strokeStyle=`rgba(0,120,0,${0.25-d/480})`; ctx.moveTo(orb.x,orb.y); ctx.lineTo(orbs[j].x,orbs[j].y); ctx.stroke(); ctx.lineWidth = 4;}
            }
        });
        requestAnimationFrame(updateOrbs);
    }
    updateOrbs();

    // ===== SCROLL ANIMATIONS =====
    const scrollElements = document.querySelectorAll(".scroll-fade, .scroll-slide");
    function elementInView(el, percent=100){ return el.getBoundingClientRect().top <= window.innerHeight*(percent/100); }
    function handleScrollAnimation(){ scrollElements.forEach(el=>el.classList.toggle("visible", elementInView(el,90))); }
    window.addEventListener("scroll", handleScrollAnimation); handleScrollAnimation();

    // ===== DRAG & DROP GALLERY =====
    let dragItem=null, offsetX=0, offsetY=0;
    document.querySelectorAll(".gallery-item").forEach(item=>{
        item.addEventListener("mousedown", e=>{
            dragItem=item; offsetX=e.clientX-item.getBoundingClientRect().left;
            offsetY=e.clientY-item.getBoundingClientRect().top;
            item.style.position="absolute"; item.style.zIndex=1000; item.style.cursor="grabbing";
        });
    });
    document.addEventListener("mousemove", e=>{
        if(dragItem){ dragItem.style.left=(e.clientX-offsetX)+"px"; dragItem.style.top=(e.clientY-offsetY)+"px"; }
    });
    document.addEventListener("mouseup", e=>{ if(dragItem){ dragItem.style.cursor="grab"; dragItem=null; } });
});
