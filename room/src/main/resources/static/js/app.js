const listings = [
  {
    id: 1,
    type: "House",
    city: "Tbilisi",
    title: "2+1 Apartment in the Center",
    location: "Saburtalo, Tbilisi",
    price: 900,
    image: "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 2,
    type: "Land",
    city: "Batumi",
    title: "Sea View Land Plot",
    location: "Makhinjauri, Batumi",
    price: 1200,
    image: "https://images.unsplash.com/photo-1500382017468-9049fed747ef?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 3,
    type: "Building",
    city: "Kutaisi",
    title: "Commercial Use Building",
    location: "Agmashenebeli, Kutaisi",
    price: 2400,
    image: "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 4,
    type: "House",
    city: "Batumi",
    title: "1+1 Close to the Beach",
    location: "New Boulevard, Batumi",
    price: 760,
    image: "https://images.unsplash.com/photo-1464890100898-a385f744067f?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 5,
    type: "Land",
    city: "Rustavi",
    title: "Large Investment Land Plot",
    location: "Industrial Zone, Rustavi",
    price: 680,
    image: "https://images.unsplash.com/photo-1479839672679-a46483c0e7c8?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 6,
    type: "Building",
    city: "Tbilisi",
    title: "Ready Office Building",
    location: "Vake, Tbilisi",
    price: 3200,
    image: "https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 7,
    type: "House",
    city: "Kutaisi",
    title: "Detached House with Garden",
    location: "Center, Kutaisi",
    price: 1100,
    image: "https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=900&q=80"
  },
  {
    id: 8,
    type: "Land",
    city: "Tbilisi",
    title: "Land Plot on Main Road",
    location: "Didi Digomi, Tbilisi",
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
    listingGrid.innerHTML = "<p>No listings found for this filter.</p>";
    resultText.textContent = "0 listings shown";
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
        <div class="price">$${item.price} / Month</div>
      </div>
    </article>
  `).join("");

  resultText.textContent = `${data.length} listings shown`;
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
