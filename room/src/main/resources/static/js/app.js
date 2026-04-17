const listings = [
  {
    id: 1,
    type: "Ev",
    city: "Tiflis",
    title: "Merkezde 2+1 Daire",
    location: "Saburtalo, Tiflis",
    price: 900,
    image: "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 2,
    type: "Arsa",
    city: "Batumi",
    title: "Deniz Manzarali Arsa",
    location: "Makhinjauri, Batumi",
    price: 1200,
    image: "https://images.unsplash.com/photo-1500382017468-9049fed747ef?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 3,
    type: "Bina",
    city: "Kutaisi",
    title: "Ticari Kullanimlik Bina",
    location: "Agmashenebeli, Kutaisi",
    price: 2400,
    image: "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 4,
    type: "Ev",
    city: "Batumi",
    title: "Sahile Yakin 1+1",
    location: "New Boulevard, Batumi",
    price: 760,
    image: "https://images.unsplash.com/photo-1464890100898-a385f744067f?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 5,
    type: "Arsa",
    city: "Rustavi",
    title: "Yatirimlik Genis Arsa",
    location: "Sanayi Bolgesi, Rustavi",
    price: 680,
    image: "https://images.unsplash.com/photo-1479839672679-a46483c0e7c8?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 6,
    type: "Bina",
    city: "Tiflis",
    title: "Hazir Ofis Binasi",
    location: "Vake, Tiflis",
    price: 3200,
    image: "https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 7,
    type: "Ev",
    city: "Kutaisi",
    title: "Bahceli Mustakil Ev",
    location: "Merkez, Kutaisi",
    price: 1100,
    image: "https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 8,
    type: "Arsa",
    city: "Tiflis",
    title: "Ana Yola Cephe Arsa",
    location: "Didi Digomi, Tiflis",
    price: 980,
    image: "https://images.unsplash.com/photo-1501854140801-50d01698950b?auto=format&fit=crop&w=900&q=80"
  }
];

const listingGrid = document.getElementById("listingGrid");
const resultText = document.getElementById("resultText");
const filterForm = document.getElementById("filterForm");
const typeSelect = document.getElementById("type");
const citySelect = document.getElementById("city");
const maxPriceInput = document.getElementById("maxPrice");

function renderCards(data) {
  if (!data.length) {
    listingGrid.innerHTML = "<p>Sonuca uygun ilan bulunamadi.</p>";
    resultText.textContent = "0 ilan listeleniyor";
    return;
  }

  listingGrid.innerHTML = data.map((item) => `
    <article class="card">
      <img src="${item.image}" alt="${item.title}">
      <div class="card-content">
        <div class="meta">
          <span class="pill">${item.type}</span>
          <span class="pill">${item.city}</span>
        </div>
        <h3>${item.title}</h3>
        <p>${item.location}</p>
        <div class="price">$${item.price} / Ay</div>
      </div>
    </article>
  `).join("");

  resultText.textContent = `${data.length} ilan listeleniyor`;
}

filterForm.addEventListener("submit", (event) => {
  event.preventDefault();

  const selectedType = typeSelect.value;
  const selectedCity = citySelect.value;
  const maxPrice = Number(maxPriceInput.value);

  const filtered = listings.filter((item) => {
    const typeOk = selectedType === "all" || item.type === selectedType;
    const cityOk = selectedCity === "all" || item.city === selectedCity;
    const priceOk = !maxPrice || item.price <= maxPrice;
    return typeOk && cityOk && priceOk;
  });

  renderCards(filtered);
});

renderCards(listings);
