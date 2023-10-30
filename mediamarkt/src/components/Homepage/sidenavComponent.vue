<template>
  <div class="bg-red-500 fixed sidenav">
    <div class="m-3 flex">
      <input type="checkbox" id="update">
      <label for="update">OUTDATED</label>
    </div>

    <div class="flex items-center">
      <div class="checkboxes">
        <br>
        <p>Brand:</p>
        <input type="checkbox" id="apple">
        <label for="apple">APPLE</label>
        <br>
        <input type="checkbox" id="acer">
        <label for="acer">ACER</label>
        <br>
        <input type="checkbox" id="hp">
        <label for="hp">HP</label>
        <br>
        <input type="checkbox" id="lenovo">
        <label for="lenovo">LENOVO</label>
        <br>
        <input type="checkbox" id="asus">
        <label for="asus">ASUS</label>
        <br>
        <input type="checkbox" id="medion">
        <label for="medion">MEDION</label>
        <br>
        <input type="checkbox" id="microsoft">
        <label for="microsoft">MICROSOFT</label>
        <br>
        <input type="checkbox" id="msi">
        <label for="msi">MSI</label>
        <br>
        <input type="checkbox" id="samsung">
        <label for="samsung">SAMSUNG</label>
        <br>
        <input type="checkbox" id="peaq">
        <label for="peaq">PEAQ</label>
        <br>
        <input type="checkbox" id="renewd">
        <label for="renewd">RENEWD</label>
      </div>
    </div>

    <div class="flex items-center">
      <div class="checkboxes">
        <br>
        <p>Brand:</p>
        <input type="checkbox" id="Core i7">
        <label for="Core i7">Core i7</label>
        <br>
        <input type="checkbox" id="Core i5">
        <label for="Core i5">Core i5</label>
        <br>
        <input type="checkbox" id="M-series">
        <label for="M-series">M-series</label>
        <br>
        <input type="checkbox" id="Core i9">
        <label for="Core i9">Core i9</label>
        <br>
        <input type="checkbox" id="Ryzen 7">
        <label for="Ryzen 7">Ryzen 7</label>
        <br>
        <input type="checkbox" id="Ryzen 5">
        <label for="Ryzen 5">Ryzen 5</label>
        <br>
        <input type="checkbox" id="Core i3">
        <label for="Core i3">Core i3</label>
        <br>
        <input type="checkbox" id="M2">
        <label for="M2">M2</label>
        <br>
        <input type="checkbox" id="Celeron">
        <label for="Celeron">Celeron</label>
        <br>
        <input type="checkbox" id="Ryzen 9">
        <label for="Ryzen 9">Ryzen 9</label>
        <br>
        <input type="checkbox" id="M1">
        <label for="M1">M1</label>
        <br>
        <input type="checkbox" id="Ryzen 3">
        <label for="Ryzen3">Ryzen 3</label>
        <br>
        <input type="checkbox" id="Pentium Silver">
        <label for="Pentium Silver">Pent. Silver</label>
        <br>
        <input type="checkbox" id="Mediatek MT">
        <label for="Mediatek MT">MediaTek MT</label>
        <br>
        <input type="checkbox" id="Pentium Gold">
        <label for="Pentium Gold">Pentium Gold</label>
        <br>
        <input type="checkbox" id="M1 Max">
        <label for="M1 Max">M1 Max</label>
        <br>
        <input type="checkbox" id="M1 Pro">
        <label for="M1 Pro">M1 Pro</label>
        <br>
        <input type="checkbox" id="N-Series">
        <label for="N-Series">N-Series</label>
        <br>
        <input type="checkbox" id="Athlon Silver">
        <label for="Athlon Silver">Athlon Silver</label>
        <br>
        <input type="checkbox" id="Snapdragon 700 Series">
        <label for="Snapdragon 700 Series">Snapdragon</label>
      </div>
    </div>

    <div>
      <p>ram</p>
      <input type="range">
    </div>
    <div class="flex items-center">
      <div class="checkboxes">
        <br>
        <p>Storage:</p>
        <input type="checkbox" id="64">
        <label for="64">64 GB</label>
        <br>
        <input type="checkbox" id="128">
        <label for="128">128 GB</label>
        <br>
        <input type="checkbox" id="256">
        <label for="256">256 GB</label>
        <br>
        <input type="checkbox" id="512">
        <label for="512">512 GB</label>
        <br>
        <input type="checkbox" id="1">
        <label for="1">1 TB</label>
        <br>
        <input type="checkbox" id="2">
        <label for="2">2 TB</label>
        <br>
        <input type="checkbox" id="4">
        <label for="4">4 TB</label>
        <br>
        <input type="checkbox" id="8">
        <label for="8">8 TB</label>
      </div>
    </div>

    <div class="flex items-center">
      <div class="checkboxes">
        <br>
        <p>GPU:</p>
        <input type="checkbox" id="Intel Iris Xe">
        <label for="Intel Iris Xe">Intel Iris Xe</label>
        <br>
        <input type="checkbox" id="RTX 4060">
        <label for="RTX 4060">RTX 4060</label>
        <br>
        <input type="checkbox" id="NVIDIA GeForce RTX 4070">
        <label for="NVIDIA GeForce RTX 4070">RTX 4070</label>
        <br>
        <input type="checkbox" id="RTX 4050">
        <label for="RTX 4050">RTX 4050</label>
        <br>
        <input type="checkbox" id="Vega 7">
        <label for="Vega 7">Vega 7</label>
        <br>
        <input type="checkbox" id="RTX 3050">
        <label for="RTX 3050">RTX 3050</label>
        <br>
        <input type="checkbox" id="RTX 3060">
        <label for="RTX 3060">RTX 3060</label>
        <br>
        <input type="checkbox" id="RTX 4080">
        <label for="RTX 4080">RTX 4080</label>
        <br>
        <input type="checkbox" id="RTX 2050">
        <label for="RTX 2050">RTX 2050</label>
        <br>
        <input type="checkbox" id="RTX 4090">
        <label for="RTX 4090">RTX 4090</label>
        <br>
        <input type="checkbox" id="RTX 3070 TI">
        <label for="RTX 3070 TI">RTX 3070 TI</label>
        <br>
        <input type="checkbox" id="Vega 8">
        <label for="Vega 8">Vega 8</label>
        <br>
        <input type="checkbox" id="Intel UHD 600">
        <label for="Intel UHD 600">UHD 600</label>
        <br>
        <input type="checkbox" id="RTX 3050 TI">
        <label for="RTX 3050 TI">RTX 3050 TI</label>
        <br>
        <input type="checkbox" id="RTX 3070">
        <label for="RTX 3070 ">RTX 3070</label>
        <br>
        <input type="checkbox" id="Vega 6">
        <label for="Vega 6">Vega 6</label>
        <br>
        <input type="checkbox" id="Arc 3">
        <label for="Arc 3">Arc 3</label>
        <br>
        <input type="checkbox" id="UHD 605">
        <label for="UHD 605">UHD 605</label>
        <br>
        <input type="checkbox" id="GTX 1650">
        <label for="GTX 1650">GTX 1650</label>
        <br>
        <input type="checkbox" id="MX 450">
        <label for="MX 450">MX 450</label>
        <br>
        <input type="checkbox" id="MX 550">
        <label for="MX 550">MX 550</label>
        <br>
        <input type="checkbox" id="Vega 3">
        <label for="Vega 3">Vega 3</label>
        <br>
        <input type="checkbox" id="RTX 3080">
        <label for="RTX 3080 ">RTX 3080</label>
        <br>
        <input type="checkbox" id="R5 230">
        <label for="R5 230">R5 230</label>
        <br>
        <input type="checkbox" id="RX 640">
        <label for="RX 640">RX 640</label>
        <br>
        <input type="checkbox" id="R5 230">
        <label for="R5 230">R5 230</label>
        <br>
        <input type="checkbox" id="RX 7600S">
        <label for="RX 7600S">RX 7600S</label>
        <br>
        <input type="checkbox" id="Arc 7">
        <label for="Arc 7">Arc 7</label>
        <br>
        <input type="checkbox" id="MX 350 ">
        <label for="MX 350 ">MX 350 </label>
        <br>
        <input type="checkbox" id="RTX 3080 TI">
        <label for="RTX 3080 TI">RTX 3080 TI</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "sidenavComponent"
}
</script>

<style scoped>
.sidenav {
  height: 100%;
  overflow-y: scroll;
  z-index: 1;
  top: 0;
  left: 0;

}

.checkboxes {
  margin-left: 10px;
}
</style>